package com.xyz.rxjava.event;

public class Event {

    /**
     * 事件类型
     */
    public String type;

    /**
     * 事件来源
     */
    public String source;

    public Event(String type, String source) {
        this.type = type;
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }
}
