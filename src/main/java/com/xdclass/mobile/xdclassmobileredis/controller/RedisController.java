package com.xdclass.mobile.xdclassmobileredis.controller;

import com.xdclass.mobile.xdclassmobileredis.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedisService redisService;

    @RequestMapping("/redis/setAndGet")
    @ResponseBody
    public String setAndGetValue(String key,String value){
        redisTemplate.opsForValue().set(key,value);
        return (String) redisTemplate.opsForValue().get(key);
    }


    @RequestMapping("/redis/setAndGet1")
    @ResponseBody
    public String setAndGetValueV2(String name,String value){
        redisService.set(name,value);
        return redisService.get(name).toString();
    }
}
