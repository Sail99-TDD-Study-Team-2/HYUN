package baseball;

public class Umpire {

    private String target;

    public Umpire() {
        this.target = "123";
    }

    public Umpire(String target) {
        this.target = target;
    }

    public Judgement judge(String guess) {
        guess = guess.replaceAll("[^0-9]","");
        if (guess.length() != 3) {
            return Judgement.FOUL;
        }
        char[] targetChars = target.toCharArray();
        for (char c : targetChars) {
            if (guess.indexOf(c) >= 0) { // 사실 유효한건 위치보다는 숫자를 포함하면 유효하다고 판단할 수 있음.
                return Judgement.VALID;
            }
        }
        return Judgement.NOTHING;
    }
}
