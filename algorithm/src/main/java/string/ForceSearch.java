package string;

public class ForceSearch {

    public static void main(String[] args) {
        String txt = "fsdagagsdgagsad";
        String pattern = "agsa";
        forceSearch(txt, pattern);
    }

    /**
     * 暴力搜索字符串
     *
     * @param txt 主串
     * @param pattern 模式匹配
     */
    private static void forceSearch(String txt, String pattern) {
        int txtLength = txt.length();
        int patternLength = pattern.length();

        for (int i = 0; i < txtLength - patternLength; i++) {
            int j;
            for (j = 0; j < patternLength; j++) {
                if (txt.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            // 找到了
            if (j == patternLength) {
                System.out.println(i);
            }
        }
    }
}
