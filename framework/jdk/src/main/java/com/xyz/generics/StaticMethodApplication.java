package com.xyz.generics;

public class StaticMethodApplication {

    public static void main(String[] args) {
        // 类的泛型定义会影响方法的泛型？可是方法的泛型完全没有使用到类的泛型。
        // 想要互相不受影响，请将泛型方法定义为static方法。

        // 对于一个raw type 类来说，非static成员函数也自动变成raw type。
        MyUtil myUtil = new MyUtil();
        Long value = (Long) myUtil.value(() -> 10L);
        System.out.println(value);


        MyUtil<String, String> myUtil1 = new MyUtil<>();
        Long value1 = myUtil1.value(() -> 10L);
        System.out.println(value1);

    }

    public static class MyUtil<K, V> {
        public <T> T value(Callback<T> callback) {
            return callback.call();
        }
    }


    public interface Callback<T> {
        T call();
    }
}




