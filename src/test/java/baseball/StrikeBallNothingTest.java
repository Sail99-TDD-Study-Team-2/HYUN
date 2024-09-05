package baseball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StrikeBallNothingTest {

    private static void assertJudgementValid(String target, String guess, ValidJudgement judge) {
        assertEquals(new Umpire(target).judgeValid(guess), judge);
    }

    private static void assertJudgement(String target, String guess, Judgement judgement) {
        char[] chars = guess.toCharArray();
        assertEquals(new Umpire(target).judge(chars, 0), judgement);
    }

    @Test
    @DisplayName("숫자가 아닌 값을 포함하면 FOUL")
    void includesNonNumber_then_FOUL() {
        String target = "123";
        String guess = "12t";
        assertJudgementValid(target, guess, ValidJudgement.FOUL);
    }

    @Test
    @DisplayName("3자리 숫자를 입력하지 않으면 FOUL")
    void notLength3_then_FOUL() {
        String target = "123";
        String guess = "12";
        String guess2 = "1234";
        assertJudgementValid(target, guess, ValidJudgement.FOUL);
        assertJudgementValid(target, guess2, ValidJudgement.FOUL);
    }

    @Test
    @DisplayName("위치와 숫자가 모두 일치하지않는 경우는 NOTHING")
    void testNothing() {
        // 정답값이 필요하다
        String target = "876";
        String guess = "123";

        assertJudgementValid(target, guess, ValidJudgement.NOTHING);
    }

    @Test
    @DisplayName("추측값이 3자리 숫자이고, 위치나 숫자가 정답값과 일치하는게 있는 경우는 유효하다고 판단한다.")
    void testValidGuess() {
        String target = "876";
        String guess = "456";

        assertJudgementValid(target, guess, ValidJudgement.VALID);
    }

    @Test
    @DisplayName("위치는 일치하지 않지만 숫자만 일치하는 경우는 BALL_맨앞자리만 판단")
    void test1Ball() {
        // 테스트의 편의를 위해 1B의 경우만 테스트.
        String target = "876";
        String guess = "612";

        assertJudgement(target, guess, Judgement.BALL);
    }

    @Test
    @DisplayName("위치와 숫자가 모두 일치하는 경우는 STRIKE_맨앞자리만 판단")
    void test1Strike() {
        String target = "876";
        String guess = "845";

        assertJudgement(target, guess, Judgement.STRIKE);
    }

    @Test
    @DisplayName("위치와 숫자가 모두 일치하지 않는 경우는 OUT_맨앞자리에 대해서만 판단")
    void test1Out() {
        String target = "876";
        String guess = "165";

        assertJudgement(target, guess, Judgement.OUT);
    }
}
