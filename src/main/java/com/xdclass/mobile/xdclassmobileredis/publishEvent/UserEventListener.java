package com.xdclass.mobile.xdclassmobileredis.publishEvent;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
//@EnableAsync //加上这个注解，表示使用非异步的方式
public class UserEventListener {
    /**
     * 使用@EventListener来监听事件。
     * condition是指条件，这里我学的为用户的姓名不为空。这里的condition使用的是SpEL表达式
     * @param user
     * @throws Exception
     */
    @EventListener(condition = "#user.name != null")
    public void handleEvent(UserDto user) throws Exception{
        System.out.println(user.getName());
        System.out.println(user.getSex());
    }
}
