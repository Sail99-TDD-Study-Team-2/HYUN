package baseball;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StrikeBallNothingTest {

    private static void assertJudgement(String guess, Judgement judge) {
        assertEquals(new Umpire().judge(guess), judge);
    }

    private static void assertJudgementWithTarget(String target, String guess, Judgement judge) {
        assertEquals(new Umpire(target).judge(guess), judge);
    }

    @Test
    @DisplayName("숫자가 아닌 값을 포함하면 FOUL")
    void includesNonNumber_then_FOUL() {
        String guess = "12t";
        assertJudgement(guess, Judgement.FOUL);
    }

    @Test
    @DisplayName("3자리 숫자를 입력하지 않으면 FOUL")
    void notLength3_then_FOUL() {
        String guess = "12";
        String guess2 = "1234";
        assertJudgement(guess, Judgement.FOUL);
        assertJudgement(guess2, Judgement.FOUL);
    }

    @Test
    @DisplayName("위치와 숫자가 모두 일치하지않는 경우는 NOTHING")
    void testNothing() {
        // 정답값이 필요하다
        String target = "876";
        String guess = "123";

        assertJudgementWithTarget(target, guess, Judgement.NOTHING);
    }

    @Test
    @DisplayName("추측값이 3자리 숫자이고, 위치나 숫자가 정답값과 일치하는게 있는 경우는 유효하다고 판단한다.")
    void testValidGuess() {
        String target = "876";
        String guess = "456";

        assertJudgementWithTarget(target, guess, Judgement.VALID);
    }

    @Test
    @DisplayName("위치는 일치하지 않지만 숫자만 일치하는 경우는 BALL")
    void testBall() {

    }
}
