package com.xdclass.mobile.xdclassmobileredis.controller;

import com.xdclass.mobile.xdclassmobileredis.RedisService;
import com.xdclass.mobile.xdclassmobileredis.autowired.Test1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisService redisService;
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

    /**
     * 测试存取大对象 所需要时间
     * @return
     */
    @RequestMapping("/testBigValue")
    @ResponseBody
    public String testBigValue() {
        List list  = new ArrayList<String>();

        for (int i = 0; i <2000000 ; i++) {
            list.add(i+"");
        }
        System.out.println((list.toString().getBytes().length)/(1024*1024) + "Mb");



        long startTime = System.currentTimeMillis();
//        redisService.set("testBigValue",list.toString());
        redisTemplate.boundValueOps("testBigValue").set(list.toString());
        long endTime = System.currentTimeMillis();

        System.out.println("存入所需时间：" + (endTime - startTime) + " ms");


        startTime = System.currentTimeMillis();
//        redisService.get("testBigValue");
        redisTemplate.boundValueOps("testBigValue").get();
        endTime = System.currentTimeMillis();
        System.out.println("获取所需时间：" + (endTime - startTime) + " ms");
        return "hello world";
    }

}
