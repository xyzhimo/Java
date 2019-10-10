package com.xyz.rxjava.event.source;

import com.xyz.rxjava.event.ClickEvent;
import com.xyz.rxjava.event.Event;
import com.xyz.rxjava.event.listener.Callback;

public class Button {

    /**
     * description: 点击传递给监听
     *
     * @param callback 事件监听器
     * @return void
     * @author: xyz
     * @create: 2019-03-27
     */
    public void onClick(Callback<Event> callback) {
        ClickEvent clickEvent = new ClickEvent("点击", "手动");
        // 事件监听器处理事件
        try {
            callback.onEvent(clickEvent);
        } catch (Exception e) {
            callback.onException(e);
        }

    }
}
