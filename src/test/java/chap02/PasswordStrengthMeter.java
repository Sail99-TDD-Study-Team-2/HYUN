package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;
        int metCounts = 0;
        // 개별 규칙을 검사하는 로직
        if (s.length() >= 8) metCounts++;
        if (meetsContainingNumberCriteria(s)) metCounts++;
        if (meetsContainingUppercaseCriteria(s)) metCounts++;

        if (metCounts <= 1) return PasswordStrength.WEAK;
        if (metCounts == 2) return PasswordStrength.NORMAL;
        return PasswordStrength.STRONG;
    }

    private static boolean meetsContainingUppercaseCriteria(String s) {
        boolean containsUpp = false;
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                containsUpp = true;
                break;
            }
        }
        return containsUpp;
    }

    private static boolean meetsContainingNumberCriteria(String s) {
        boolean containsNum = false;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                containsNum = true;
                break;
            }
        }
        return containsNum;
    }
}
