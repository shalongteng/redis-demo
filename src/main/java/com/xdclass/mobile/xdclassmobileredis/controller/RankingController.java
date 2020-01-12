package com.xdclass.mobile.xdclassmobileredis.controller;

import com.xdclass.mobile.xdclassmobileredis.service.RangingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class RankingController {


    @Autowired
    private RangingService rankingService;

    /**
     * 初始化时候 添加分数
     * @param uid
     * @param score
     * @return
     */
    @ResponseBody
    @RequestMapping("/addScore")
    public String addRank(String uid, Integer score) {
        rankingService.rankAdd(uid, score);
        return "success";
    }


    /**
     * 增加分数
     * @param uid
     * @param score
     * @return
     */
    @ResponseBody
    @RequestMapping("/increScore")
    public String increScore(String uid, Integer score) {
        rankingService.increSocre(uid, score);
        return "success";
    }

    /**
     * 获取 某个uid 的排名
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("/rank")
    public Map<String, Long> rank(String uid) {
        Map<String, Long> map = new HashMap<>();
        map.put(uid, rankingService.rankNum(uid));
        return map;
    }

    /**
     * 获取某个uid 分数
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("/score")
    public Long rankNum(String uid) {
        return rankingService.score(uid);
    }

    /**
     * 获取排行
     * @param start
     * @param end
     * @return
     */
    @ResponseBody
    @RequestMapping("/scoreByRange")
    public Set<ZSetOperations.TypedTuple<Object>> scoreByRange(Integer start, Integer end) {
        return rankingService.rankWithScore(start,end);
    }


    /**
     * 添加/增加 用户积分
     * @param uid
     * @param score
     * @return
     */
    @ResponseBody
    @RequestMapping("/sale/increScore")
    public String increSaleScore(String uid, Integer score) {
        rankingService.increSaleSocre(uid, score);
        return "success";
    }

    /**
     * 获取用户 分数 和排名
     * @param uid
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("/sale/userScore")
    public Map<String,Object> userScore(String uid,String name) {
        return rankingService.userRank(uid,name);
    }

    /**
     * 倒叙 获取前 多少名
     * @param start
     * @param end
     * @return
     */
    @ResponseBody
    @RequestMapping("/sale/top")
    public List<Map<String,Object>> reverseZRankWithRank(long start,long end) {
        return rankingService.reverseZRankWithRank(start,end);
    }

    /**
     * 根据分数获取 排名
     * @param start
     * @param end
     * @return
     */
    @ResponseBody
    @RequestMapping("/sale/scoreByRange")
    public List<Map<String,Object>> saleScoreByRange(Integer start, Integer end) {
        return rankingService.saleRankWithScore(start,end);
    }

}
