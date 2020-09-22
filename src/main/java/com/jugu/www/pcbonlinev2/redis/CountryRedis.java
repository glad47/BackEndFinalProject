package com.jugu.www.pcbonlinev2.redis;

import com.jugu.www.pcbonlinev2.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 城市缓存redis
 */
@Component
public class CountryRedis {

    private final RedisUtil redisUtil;

    private final String country_redis_key = "country:all:list";

    @Autowired
    public CountryRedis(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    public void save(List data){
        if (data.size() == 0) return;
        redisUtil.lSet(country_redis_key,data);
    }

    public List<Object> get(){
        return redisUtil.lGet(country_redis_key);
    }

    public void delete(){
        redisUtil.delete(country_redis_key);
    }

}
