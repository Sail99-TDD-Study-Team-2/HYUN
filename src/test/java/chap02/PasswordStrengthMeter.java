package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;
        if (s.length() < 8) {
            return PasswordStrength.NORMAL;
        }
        boolean containsNum = meetsContainingNumberCriteria(s);
        if (!containsNum) return PasswordStrength.NORMAL;
        boolean containsUpp = meetsContainingUppercaseCriteria(s);
        if (!containsUpp) return PasswordStrength.NORMAL;
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
