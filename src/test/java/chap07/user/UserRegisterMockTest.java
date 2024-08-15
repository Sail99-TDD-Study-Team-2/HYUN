package chap07.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

public class UserRegisterMockTest {
    private UserRegister userRegister;
    private WeakPasswordChecker mockPasswordChecker = mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = mock(SpyEmailNotifier.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker,fakeRepository, mockEmailNotifier);
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword() {
        given(mockPasswordChecker.checkPasswordWeak("pw"))
                .willReturn(true);

        assertThrows(
                WeakPasswordException.class,
                () -> {
                    userRegister.register("id","pw","email");
        });
    }

    @Test
    @DisplayName("회원 가입시 암호 검사 수행함") // 스텁
    void checkPassword() {
        userRegister.register("id", "pw", "email");

        then(mockPasswordChecker)
                .should()
                .checkPasswordWeak(anyString()); // 임의의 String 인자를 이용해서 checkPasswordWeak()메서드 호출 여부 확인
    }

    @Test
    @DisplayName("가입하면 메일을 전송함") // 스파이
    void whenRegisterThenSendMail() {
        userRegister.register("id","pw","email@email.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(mockEmailNotifier)
                .should().sendRegisterEmail(captor.capture());

        String realEmail = captor.getValue();
        assertEquals(
                "email@email.com",
                realEmail
        );
    }
}
