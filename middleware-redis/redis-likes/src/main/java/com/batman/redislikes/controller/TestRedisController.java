package com.batman.redislikes.controller;

import com.batman.redislikes.entity.UserLike;
import com.batman.redislikes.model.LikedCountDTO;
import com.batman.redislikes.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("test/redis")
public class TestRedisController {
    @Autowired
    RedisService redisService;

    /**
     * 点赞。状态为1
     *
     * @param likedUserId
     * @param likedPostId
     */
    @GetMapping("saveLiked2Redis")
    public void saveLiked2Redis(String likedUserId, String likedPostId) {
        redisService.saveLiked2Redis(likedUserId, likedPostId);
    }

    /**
     * 取消点赞。将状态改变为0
     *
     * @param likedUserId
     * @param likedPostId
     */
    @GetMapping("unlikeFromRedis")
    public void unlikeFromRedis(String likedUserId, String likedPostId) {
        redisService.unlikeFromRedis(likedUserId, likedPostId);
    }

    /**
     * 从Redis中删除一条点赞数据
     *
     * @param likedUserId
     * @param likedPostId
     */
    @GetMapping("deleteLikedFromRedis")
    public void deleteLikedFromRedis(String likedUserId, String likedPostId) {
        redisService.deleteLikedFromRedis(likedUserId, likedPostId);
    }

    /**
     * 该用户的点赞数加1
     *
     * @param likedUserId
     */
    @GetMapping("incrementLikedCount")
    public void incrementLikedCount(String likedUserId) {
        redisService.incrementLikedCount(likedUserId);
    }

    /**
     * 该用户的点赞数减1
     *
     * @param likedUserId
     */
    @GetMapping("decrementLikedCount")
    public void decrementLikedCount(String likedUserId) {
        redisService.decrementLikedCount(likedUserId);
    }

    /**
     * 获取Redis中存储的所有点赞数据
     *
     * @return
     */
    @GetMapping("getLikedDataFromRedis")
    public List<UserLike> getLikedDataFromRedis(){
        return redisService.getLikedDataFromRedis();
    }

    /**
     * 获取Redis中存储的所有点赞数量
     *
     * @return
     */
    @GetMapping("getLikedCountFromRedis")
    public List<LikedCountDTO> getLikedCountFromRedis(){
        return redisService.getLikedCountFromRedis();
    }

}
