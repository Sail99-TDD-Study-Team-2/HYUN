package baseball;

public class Umpire {

    private final String target;

    public Umpire() {
        this.target = "123";
    }

    public Umpire(String target) {
        this.target = target;
    }

    /**
     * 추측값의 유효성 판단
     */
    public ValidJudgement judgeValid(String guess) {
        guess = guess.replaceAll("[^0-9]","");
        if (guess.length() != 3) {
            return ValidJudgement.FOUL;
        }
        char[] targetChars = target.toCharArray();
        for (char c : targetChars) {
            if (guess.indexOf(c) >= 0) { // 사실 유효한건 위치보다는 숫자를 포함하면 유효하다고 판단할 수 있음.
                return ValidJudgement.VALID;
            }
        }
        return ValidJudgement.NOTHING;
    }

    /**
     * 추측값 한자리에 대한 strike, ball 판단
     */
    public Judgement judge(char[] guess, int index) {
        char[] targets = target.toCharArray();
        if (guess[index] == targets[index]) {
            return Judgement.STRIKE;
        } else if (target.indexOf(guess[index]) >= 0) {
            return Judgement.BALL;
        }
        return Judgement.OUT;
    }
}
