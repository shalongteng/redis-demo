package com.xdclass.mobile.xdclassmobileredis.publishEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/event")
public class PublishEventController {
    @Autowired
    private UserEventRegister userEventRegister;


    /**
     * Spring的publish-event使用的是监听者模式
     * 监听者模式包含了一个监听者Listener与之对应的事件Event，还有一个事件发布者EventPublish，过程就是EventPublish发布一个事件，
     * 被监听者捕获到，然后执行事件相应的方法
     * @throws Exception
     */
    @GetMapping("/registerUser")
    @ResponseBody
    public void register() throws Exception {
        userEventRegister.register();
    }
}
