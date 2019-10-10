package com.xyz.load;

public class A {

    public static B b = new B();

    static {
        System.out.println("A 静态初始化");
    }

    A() {
        System.out.println("A 构造方法初始化");
    }

    {
        System.out.println("A 代码初始化");
    }

}
