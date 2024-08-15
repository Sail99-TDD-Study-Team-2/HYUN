package mockito;

import chap07.user.EmailNotifier;
import chap07.user.UserRegister;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

public class GameGenMockTest {

    @Test
    void mockTest() {
        // 1. 모의 객체 생성
        GameNumGen genMock = mock(GameNumGen.class);
        /*
        * 2. 스텁 설정
        * given() : 스텁을 정의할 모의 객체의 메서드 호출을 전달
        * willReturn() : 스텁을 정의한 메서드가 리턴할 값을 지정
        * willThrow() : 리턴값 지정 대신 발생할 익셉션 지정
        * */
        given(genMock.generate(GameLevel.EASY)).willReturn("123");

        // 3. 스텁 설정에 매칭되는 메서드 실행
        String num = genMock.generate(GameLevel.EASY);
        assertEquals("123", num);
    }

    @Test
    void mockThrowTest() {
        GameNumGen genMock = mock(GameNumGen.class);
//        given(genMock.generate(null)).willThrow(IllegalArgumentException.class);
        given(genMock.generate(null)).willThrow(new IllegalArgumentException()); // 타입이나 익셉션 객체를 인자로 전달

        assertThrows(
                IllegalArgumentException.class,
                () -> genMock.generate(null)
        );
    }

    @Test
    void voidMethodWillThrowTest() {
        List<String> mockList = mock(List.class);
        willThrow(UnsupportedOperationException.class)
                .given(mockList)
                .clear();

        assertThrows(
                UnsupportedOperationException.class,
                () -> mockList.clear()
        );
    }

    @Test
    @DisplayName("인자 매칭 처리")
    void anyMatchTest() {
        GameNumGen genMock = mock(GameNumGen.class);
        given(genMock.generate(any())).willReturn("456");

        String num = genMock.generate(GameLevel.EASY);
        assertEquals("456", num);

        String num2 = genMock.generate(GameLevel.NORMAL);
        assertEquals("456",num2);
    }

    @Test
    @DisplayName("인자 매칭 처리2 - 임의값일치와 정확한 값 일치가 필요한 경우")
    void mixAnyAndEq() {
        List<String> mockList = mock(List.class);

        given(mockList.set(anyInt(), eq("123"))).willReturn("456");

        String old = mockList.set(5, "123");
        assertEquals("456",old);
    }

    @Test
    @DisplayName("행위검증")
    void init() {
        GameNumGen genMock = mock(GameNumGen.class);
        Game game = new Game(genMock);
        game.init(GameLevel.EASY);

        // BDDMockito.then() -> 메서드 호출 여부를 검증할 모의 객체를 전달받는다.
        // should() -> 모의 객체의 메서드가 불려야 한다. 그 뒤에 실제로 불려야 할 메서드 지정
        then(genMock).should(only()).generate(GameLevel.EASY);
    }

}
