public class FibonacciRecursion {

    public static void main(String[] args) {
        int fibonacci = fibonacci1(10);
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

    public static int fibonacci1(int n) {
        int sum0 = 1;
        int sum1 = 1;
        int sum = 0;
        if (n == 1) {
            return sum0;
        }
        if (n == 2) {
            return sum1;
        }
        for (int i = 2; i < n; i++) {
            sum = sum0 + sum1;
            sum0 = sum1;
            sum1 = sum;
        }
        return sum;
    }

}
