package sort;

import java.util.Arrays;

public class QuickSort {

    private static int[] a = new int[]{1, 5, 3, 7, 8, 4, 2, 6};

    public static void sort0(int start, int end) {
        if (start >= end) return;
        int partition = partition(start, end);
        sort0(start, partition - 1);
        sort0(partition + 1, end);
    }

    /**
     * 分区中进行排序
     * 原地分区法
     * i 指针指向的一定是大于等于分区点的数值
     * j 指针是为了寻找
     */
    private static int partition(int start, int end) {
        int pivot = end;
        int i = start;
        for (int j = start; j < end; j++) {
            if (a[j] < a[pivot]) {
                // 和 i 指向的位置值交换
                int tmp = a[j];
                a[j] = a[i];
                a[i] = tmp;
                i++;
            }
        }
        // 交换 i 和 pivot 的值
        int tmp = a[i];
        a[i] = a[pivot];
        a[pivot] = tmp;
        return i;
    }

    public static int searchK(int K, int start, int end) {

        int pivot = end;
        int i = start;
        for (int j = start; j < end; j++) {
            if (a[j] < a[pivot]) {
                int tmp = a[j];
                a[j] = a[i];
                a[i] = tmp;
                i++;
            }
        }
        int tmp = a[i];
        a[i] = a[pivot];
        a[pivot] = tmp;
        if (i < K) {
            return searchK(K, i + 1, end);
        } else if (i > K) {
            return searchK(K, 0, i - 1);
        } else {
            return a[i];
        }
    }


    public static void main(String[] args) {
//        System.out.println(Arrays.toString(a));
//        sort0(0, a.length - 1);
//        System.out.println(Arrays.toString(a));

        int num = searchK(3, 0, a.length - 1);
        System.out.println(num);
    }
}
