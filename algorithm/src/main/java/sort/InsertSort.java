package sort;

import java.util.Arrays;

/**
 * 未排序区间和已排序区间
 * 插入排序，和已排序区间最后一位一直向前比较，如果比较已排序小的话，已排序的位置往后移动一位
 */
public class InsertSort {

    private static int[] a = new int[]{1, 5, 3, 4, 2, 6};

    public static void sort() {
        for (int i = 1; i < a.length; i++) {
            int value = a[i];
            // 已排序区间，往前不断比较
            for (int j = i - 1; j >= 0; j--) {
                // 如果比已排序小的话，已排序往后移动
                if (value < a[j]) {
                    a[j + 1] = a[j];
                } else {
                    // 如果比已排序大的话
                    a[j + 1] = value;
                    break;
                }
            }
        }
    }

    public static void sort0() {
        for (int i = 1; i < a.length; i++) {
            int value = a[i];
            // 拿这个数和之前的数进行一个一个比较
            // 往后面开始，是因为好移动
            for (int j = i - 1; j >= 0; j--) {
                if (value < a[j]) {
                    a[j + 1] = a[j];
                } else {
                    a[j + 1] = value;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(a));
        sort0();
        System.out.println(Arrays.toString(a));
    }
}
