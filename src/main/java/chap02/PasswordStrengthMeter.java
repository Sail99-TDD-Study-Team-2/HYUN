package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        // 암호가 null이거나 빈 문자열이면 암호 강도는 INVALID
        if (s == null || s.isEmpty()) return PasswordStrength.INVALID;
        // 충족하는 규칙 개수 산출
        int metCounts = getMetCriteriaCounts(s);
        // 충족하는 규칙 개수 1 이하 -> WEAK
        if (metCounts <= 1) return PasswordStrength.WEAK;
        // 충족하는 규칙 개수 2 -> NORMAL
        if (metCounts == 2) return PasswordStrength.NORMAL;
        // 그 외(충족 규칙 개수 2 초과) -> STRONG
        return PasswordStrength.STRONG;
    }

    private static int getMetCriteriaCounts(String s) {
        int metCounts = 0;
        if (s.length() >= 8) metCounts++;
        if (meetsContainingNumberCriteria(s)) metCounts++;
        if (meetsContainingUppercaseCriteria(s)) metCounts++;
        return metCounts;
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
