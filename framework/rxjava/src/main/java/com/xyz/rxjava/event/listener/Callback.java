package com.xyz.rxjava.event.listener;

public interface Callback<T> {

    void onEvent(T e);

    void onException(Throwable t);


}
