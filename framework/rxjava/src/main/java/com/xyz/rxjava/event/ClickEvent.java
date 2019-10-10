package com.xyz.rxjava.event;

public class ClickEvent extends Event {

    public ClickEvent(String type, String source) {
        super(type, source);
    }

    @Override
    public String toString() {
        return "ClickEvent{" +
                "type='" + type + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
