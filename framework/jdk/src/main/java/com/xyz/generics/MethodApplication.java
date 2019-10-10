package com.xyz.generics;

public class MethodApplication<S> {

    public <T extends Number> T print(T o) {
        return o;
    }

    public static void main(String[] args) {
        MethodApplication application = new MethodApplication();
        Integer s = 1;
        application.print(1);
    }

}
