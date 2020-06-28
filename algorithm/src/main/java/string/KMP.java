package string;

/**
 * 获取 next 数组
 * eg abcdefg = 0111111
 * eg abcababca = -100012123
 * eg abababca = -10012340
 */
public class KMP {

    public static void main(String[] args) {
//        char[] input = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'};
//        char[] input = new char[]{'a', 'b', 'c', 'a', 'b', 'x'};
//        char[] input = new char[]{'a', 'b', 'c', 'a', 'b', 'a','b','c','a'};
        char[] all = new char[]{'c', 'd', 'a', 'b', 'a', 'b', 'a', 'b', 'a', 'b', 'c', 'a', 'd', 'b'};
        char[] partition = new char[]{'a', 'b', 'a', 'b', 'a', 'b', 'c', 'a'};
        int match = match(all, partition);
        System.out.println(match);
    }

    private static int[] getNextArray(char[] input) {
        int[] output = new int[input.length];
        int i = 1;
        int j = 0;
        output[0] = -1;

        while (i < input.length - 1) {
            if (j == -1 || input[i] == input[j]) {
                i++;
                j++;
                output[i] = j;
            } else {
                // 回溯到上一个前后缀匹配字符的位置
                j = output[j];
            }
        }
        return output;
    }

    public static int match(char[] all, char[] part) {
        int i = 0;
        int j = 0;
        int[] next = getNextValArray(part);
        while (i < all.length && j < part.length) {
            if (j == -1 || all[i] == part[j]) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == part.length) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static int[] getNextValArray(char[] input) {
        int[] output = new int[input.length];
        int i = 0;
        int j = -1;
        output[0] = -1;
        while (i < input.length - 1) {
            if (j == -1 || input[i] == input[j]) {
                i++;
                j++;
                if (input[i] != input[j]) {
                    output[i] = j;
                } else {
                    output[i] = output[j];
                }
            } else {
                j = output[j];
            }
        }
        return output;
    }

}
