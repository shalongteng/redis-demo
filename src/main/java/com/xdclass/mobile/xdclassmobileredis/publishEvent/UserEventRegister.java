package com.xdclass.mobile.xdclassmobileredis.publishEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class UserEventRegister {
    @Autowired
    private ApplicationEventPublisher publisher;


    @Autowired
    private ApplicationContext applicationContext;
 
    public void register() throws Exception {
        UserDto user=new UserDto();
        user.setName("电脑");
        user.setSex("男");
        publisher.publishEvent(user);
//        applicationContext.publishEvent(user);
    }
}
