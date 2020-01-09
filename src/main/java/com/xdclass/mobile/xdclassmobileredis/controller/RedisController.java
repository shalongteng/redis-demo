package com.xdclass.mobile.xdclassmobileredis.controller;

import com.xdclass.mobile.xdclassmobileredis.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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

    /**
     * 测试redisTemplate 使用
     * @param key
     * @param value
     * @return
     */
    @RequestMapping("/redis/setAndGet")
    @ResponseBody
    public String setAndGetValue(String key,String value){
        ValueOperations valueOperations = redisTemplate.opsForValue();
//        valueOperations.set(key,value);
        valueOperations.getAndSet(key,value);
        return (String) valueOperations.getAndSet(key,value);
    }

    /**
     * 测试redis工具类使用
     * @param key
     * @param value
     * @return
     */
    @RequestMapping("/redis/setAndGet1")
    @ResponseBody
    public String setAndGetValueV2(String key,String value){
        redisService.set(key,value);
        return redisService.get(key).toString();
    }
}
