package sort;

import java.util.Arrays;

/**
 * 冒泡思想就是大数最早排后面, 也可以实现一套小数最早排前面
 */
public class BubbleSort {

    private static int[] a = new int[]{1, 5, 3, 4, 2, 6};

    public static void sort0() {
        for (int i = 0; i < a.length; i++) {
            for (int j = 1; j < a.length - i; j++) {
                if (a[j - 1] > a[j]) {
                    int tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;
                }
            }
        }
    }

    public static void sort01() {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int tmp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tmp;
                }
            }
        }
    }


    public static void sort1() {
        for (int i = 1; i < a.length; i++) {
            for (int j = a.length - 1; j >= i; j--) {
                if (a[j - 1] > a[j]) {
                    int tmp = a[j - 1];
                    a[j - 1] = a[j];
                    a[j] = tmp;
                }
            }
        }

    }


    public static void sort11() {
        for (int i = 0; i < a.length; i++) {
            for (int j = a.length - 1; j > i; j--) {
                if (a[j] < a[j - 1]) {
                    int tmp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(a));
        sort11();
        System.out.println(Arrays.toString(a));
    }
}
