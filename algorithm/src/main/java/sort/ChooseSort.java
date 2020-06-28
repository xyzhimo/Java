package sort;

import java.util.Arrays;

/**
 * 选择排序不是稳定排序
 * 比如 5，8，5，2，9 这样一组数据，使用选择排序算法来排序的话，
 * 第一次找到最小元素 2，与第一个 5 交换位置，那第一个 5 和中间的 5 顺序就变了，所以就不稳定了。
 */
public class ChooseSort {

    private static int[] a = new int[]{1, 5, 3, 4, 2, 6};

    public static void sortX() {
        for (int i = 0; i < a.length; i++) {
            int min = a[i];
            for (int j = i + 1; j < a.length; j++) {
                if (min > a[j]) {
                    min = a[j];
                }
            }
            // 错误示范, 这样赋值之后, a[i] 原本的值就没了
            // 解决思路, 需要 a[i] 和 {a[?]=min} 值替换
            a[i] = min;
        }
    }

    public static void sort() {
        for (int i = 0; i < a.length; i++) {
            int min = a[i];
            int pos = i;
            for (int j = i + 1; j < a.length; j++) {
                if (min > a[j]) {
                    min = a[j];
                    pos = j;
                }
            }
            if (pos != i) {
                a[pos] = a[i];
                a[i] = min;
            }
        }
    }

    public static void sort0() {
        for (int i = 0; i < a.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = a[i];
                a[i] = a[minIndex];
                a[minIndex] = tmp;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(a));
        sort0();
        System.out.println(Arrays.toString(a));
    }
}
