package sort;

import java.util.Arrays;

/**
 * 理解归并排序就是一个公式 sort(p,r) = merge(sort(p,q),sort(q+1,r)), 终止条件就是 p=r, 表示拆不动了
 * 总之就是先二分拆, 拆了合, 合的过程中排序, 建议参考 merge0 方法, merge 方法是我一开始写的, 差距太大了
 */
public class MergeSort {

    private static int[] a = new int[]{1, 5, 3, 4, 2, 6};

    public static void sort0(int[] tmp, int start, int end) {
        if (start == end) return;
        int position = (start + end) / 2;
        sort0(tmp, start, position);
        sort0(tmp, position + 1, end);

        merge0(tmp, start, position, end);
    }

    private static void merge(int start, int position, int end) {
        int[] tmp = new int[end - start + 1];
        int i = start;
        int j = position + 1;
        int pos = 0;

        while (i <= position && j <= end) {
            if (a[i] < a[j]) {
                tmp[pos] = a[i];
                i++;
            } else {
                tmp[pos] = a[j];
                j++;
            }
            pos++;

            if (i > position) {
                // j 还有剩余
                for (int k = j; k <= end; k++) {
                    tmp[pos] = a[k];
                    pos++;
                }
                break;
            }
            if (j > end) {
                // i 还有剩余
                for (int k = i; k <= position; k++) {
                    tmp[pos] = a[k];
                    pos++;
                }
                break;
            }
        }
        for (int value : tmp) {
            a[start] = value;
            start++;
        }
    }

    private static void merge0(int[] tmp, int start, int position, int end) {
        // 把需要排序数组复制进临时数组
        for (int k = start; k <= end; k++) {
            tmp[k] = a[k];
        }
        int i = start;
        int j = position + 1;
        // 用两个指针把临时数组的值, 一一比较再放入原数组中
        // 因为临时数组和原数组一样大小, 我们完全不需要考虑下标对齐的问题
        for (int k = start; k <= end; k++) {
            if (i > position) {
                a[k] = tmp[j++];
            } else if (j > end) {
                a[k] = tmp[i++];
                // 保证排序算法的稳定性
            } else if (tmp[i] < tmp[j]) {
                a[k] = tmp[i++];
            } else {
                a[k] = tmp[j++];
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(a));
        // 初始化一个数组大小为排序数组的大小
        // 无论如何也节省不了的空间, 不如提早创建, 有助于后续代码改进
        int[] tmp = new int[a.length];
        sort0(tmp, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
