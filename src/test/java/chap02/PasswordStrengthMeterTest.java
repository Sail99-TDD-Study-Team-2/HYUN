package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {

    /*
     * 검사할 규칙 세가지
     * 1. 길이가 8글자 이상
     * 2. 0부터 9사이의 숫자를 포함
     * 3. 대문자 포함
     *
     * 세가지 모두 충족시 - 강함
     * 2 충족시 - 보통
     * 1 이하 충족시 - 약함
     * */

    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String pw, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(pw);
        assertEquals(expStr, result);
    }

    // 1. 모든 규칙을 충족하는 경우
    @DisplayName("암호가_모든_조건을_충족하면_암호강도는_강함이어야함")
    @Test
    void meetsAllCriteria_Then_Strong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    // 2. 길이만 8글자 미만이고 나머지 조건은 충족하는 경우
    @Test
    @DisplayName("길이_조건만_미충족시_보통이다")
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
    }

    // 3. 숫자를 포함하지 않고 나머지 조건은 충족하는 경우
    @Test
    @DisplayName("숫자_조건만_미충족시_보통이다")
    void meetsOtherCriteria_except_for_number_Then_Noraml() {
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    // 4. 값이 없는 경우
    @Test
    @DisplayName("값이_없는_경우")
    void nullInput_Then_Invalid() {
        assertStrength(null,PasswordStrength.INVALID);
    }

    // 5. 대문자를 포함하지 않고 나머지 조건을 충족하는 경우
    @Test @DisplayName("대문자만_없는경우_보통이다")
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal() {
        assertStrength("ab12!@df",PasswordStrength.NORMAL);
    }

    // 6. 길이가 8글자 이상인 조건만 충족하는 경우
    @Test @DisplayName("길이조건만_충족하는_경우는_약함이다")
    void meetsOnlyLengthCriteria_Then_Weak() {
        assertStrength("abdefghi",PasswordStrength.WEAK);
    }

    // 7. 숫자 조건만 충족하는 경우
    @Test
    @DisplayName("숫자_조건만_충족하면_약함이다")
    void meetsOnlyNumCriteria_Then_Weak() {
        assertStrength("12345", PasswordStrength.WEAK);
    }

    // 8. 대문자 포함 조건만 충족하는 경우
    @Test
    @DisplayName("대문자_조건만_충족시_약함이다")
    void meetsOnlyUpperCrtieria_Then_Weak() {
        assertStrength("ABZEF", PasswordStrength.WEAK);
    }

}

