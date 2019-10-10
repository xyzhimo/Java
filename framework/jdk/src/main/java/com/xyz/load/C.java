package com.xyz.load;

public class C {

    A a;

    static { System.out.println("C 静态初始化"); }

    { System.out.println("C 普通代码初始化"); }

    public C() {
        System.out.println("C 构造方法初始化");
    }

    public static class D {

        static { System.out.println("D 静态初始化"); }

        { System.out.println("D 普通代码初始化"); }

        public D() {
            System.out.println("D 构造方法初始化");
        }

//        public void test() {
//            A a = C.this.a;
//        }
    }

    public class F {
        public void test() {
            A a = C.this.a;
        }
    }

    public static void main(String[] args) {
        C c = new C();
        C.F f = c.new F();
//        C.F f1 = new C.F();

//        C.D d = c.new D();
        C.D d1 = new C.D();

    }
}
