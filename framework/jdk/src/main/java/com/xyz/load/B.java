package com.xyz.load;

public class B {

    static {
        System.out.println("B 静态初始化");
    }

    public B() {
        System.out.println("B 构造方法初始化");
    }

    {
        System.out.println("B 代码初始化");
    }

}
