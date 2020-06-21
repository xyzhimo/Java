public class FibonacciRecursion {

    public static void main(String[] args) {
        int fibonacci = fibonacci(5);
        System.out.println(fibonacci);
    }

    /**
     * 甲酸第 n 个数的斐波那契数数值
     *
     * @param n 第 n 个
     * @return 第 n 个数的斐波那契数数值
     */
    public static int fibonacci(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }
        return fibonacci(n - 2) + fibonacci(n - 1);
    }

}
