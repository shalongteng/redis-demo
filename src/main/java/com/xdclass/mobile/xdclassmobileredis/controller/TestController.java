package com.xdclass.mobile.xdclassmobileredis.controller;

import com.xdclass.mobile.xdclassmobileredis.autowired.Test1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;
//    @Resource
//    private ScoreFlowMapper scoreFlowMapper;


    @Autowired
    private Collection<Test1> test1s;

    /**
     * @Autowired 注入集合类，可以将所有实现该接口的实现类，都注入进来
     * @return
     */
    @RequestMapping("/autowireTest")
    @ResponseBody
    public String autowireTest() {
        test1s.stream().forEach(
                test1 -> System.out.println(test1.hello())
        );
        return "hello world";
    }

    /**
     * 测试springboot web 项目搭建启动是否成功
     * @return
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello world";
    }


    @RequestMapping("/hashTest")
    @ResponseBody
    public String hashTest() {
        HashOperations hashOperations = redisTemplate.opsForHash();

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("key1", "0");
//        param.put("key2", "b");
//        param.put("key3", "c");
        hashOperations.putAll("he2", param);
        hashOperations.get("he2","key1");
        redisTemplate.delete("he2");
        Map he2 = hashOperations.entries("he2");
        return null;
    }



//    @RequestMapping("/getFlow")
//    @ResponseBody
//    public ScoreFlow getFlow() {
//        ScoreFlowExample example = new ScoreFlowExample();
//        ScoreFlowExample.Criteria criteria = example.createCriteria();
//        criteria.andUserIdEqualTo(1);
//        return scoreFlowMapper.selectByExample(example).get(0);
//    }




}
