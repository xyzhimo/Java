package recursion;

public class RecursionTree {

    public static void printPermutations(int[] data, int n, int k) {
        if (k == 1) {
            for (int i = 0; i < n; ++i) {
                System.out.print(data[i] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < k; ++i) {
            // 每次选择一位交换, 包括与自身交换
            // 只要做到每次都是不同的数字即可
            int tmp = data[i];
            data[i] = data[k - 1];
            data[k - 1] = tmp;

            printPermutations(data, n, k - 1);

            // 还原原本的数组模样
            // 这样下次交换的时候, 就不会出现相同的排列组合了
            tmp = data[i];
            data[i] = data[k - 1];
            data[k - 1] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2};
        printPermutations(a, 2, 2);
    }

}
