package search;

/**
 * 上一节我说过，凡是用二分查找能解决的，绝大部分我们更倾向于用散列表或者二叉查找树。即便是二分查找在内存使用上更节省，但是毕竟内存如此紧缺的情况并不多。
 * 那二分查找真的没什么用处了吗？实际上，上一节讲的求“值等于给定值”的二分查找确实不怎么会被用到，
 * 二分查找更适合用在“近似”查找问题，在这类问题上，二分查找的优势更加明显。
 * 比如今天讲的这几种变体问题，用其他数据结构，比如散列表、二叉树，就比较难实现了。
 */
public class BinarySearch {

    private static int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 10, 10, 10, 11, 12, 13, 14};

    public static int search(int value) {
        int lo = 0;
        int hi = a.length - 1;
        // 错误: lo < hi 如果数组只有一个数字，岂不是永远都是 -1 咯
        // 正确: lo <= hi
        while (lo <= hi) {
            // 优化, lo + hi 有可能造成数字溢出
            // int mid = lo + ((hi - lo) >> 1)
            int mid = (lo + hi) / 2;
            if (a[mid] > value) {
                hi = mid - 1;
            } else if (a[mid] < value) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int searchFirst(int value) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            // 中间值大于等于的话, 证明真正的值可能还在前面, 往前找
            // 这样相当于等于的话直接丢弃, 后续再找回来
            if (a[mid] >= value) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        // 跳出循环的前提条件是 hi 和 lo 已经指向同一个元素了，那么跳出只有两种可能
        // 一种是最后一个数大于 value 要么等于 value, hi 往前进, 跳出
        // 一种是最后一个数小于 value, lo 往后推, 指向比最后一个数大的数值（可能是一开始找到, 结果被 hi 指针扔掉的值）
        if (lo < a.length && a[lo] == value) {
            return lo;
        } else {
            return -1;
        }
    }

    public static int searchLast(int value) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (a[mid] <= value) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        if (hi >= 0 && a[hi] == value) {
            return hi;
        } else {
            return -1;
        }
    }

    public static int searchGtEFirst(int value) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (a[mid] < value) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        if (lo < a.length) {
            return lo;
        } else {
            return -1;
        }
    }

    public static int searchLtELast(int value) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (a[mid] > value) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        // 如果最后一个值是小于等于 value 的话, hi 不动, lo + 1
        if (hi >= 0) {
            return hi;
        } else {
            return -1;
        }
    }


    public static void main(String[] args) {
        int index = searchFirst(10);
        System.out.println(index);

        index = searchLast(10);
        System.out.println(index);

        index = searchGtEFirst(9);
        System.out.println(index);

        index = searchLtELast(9);
        System.out.println(index);

        index = search0(2);
        System.out.println(index);
    }

    /**
     * 循环数组思考题
     */
    public static int search0(int value) {
        int[] b = new int[]{4, 5, 6, 1, 2, 3};
        int lo = 0;
        int hi = 0;
        int length = b.length;
        for (int i = 0; i < length - 1; i++) {
            if (b[i + 1] < b[i]) {
                lo = i + 1;
                hi = i + length;
            }
        }

        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (b[mid % length] < value) {
                lo = mid + 1;
            } else if (b[mid % length] > value) {
                hi = mid - 1;
            } else {
                return mid % length;
            }
        }
        return -1;
    }

}
