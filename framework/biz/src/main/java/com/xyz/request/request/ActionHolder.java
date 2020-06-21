package com.xyz.request.request;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ActionHolder implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Map<String, Action> actions;

    /**
     * 取得Spring容器里的Action
     *
     * @param
     * @return
     */
    public Action getAction(String actionName) {
        if (null == actions) {
            synchronized (this) {
                if (null == actions) {
                    actions = new HashMap<String, Action>();
                    Map<String, Action> map = applicationContext.getBeansOfType(Action.class);
                    for (Action act : map.values()) {
                        actions.put(act.getName(), act);
                    }
                }

            }
        }
        return actions.get(actionName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
