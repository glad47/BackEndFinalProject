package com.jugu.www.pcbonlinev2.utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisUtil {
    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;

    private final static Gson gson = new Gson();

    private RedisTemplate<String, Object> redisTemplate;

    private ValueOperations<String, Object> valueOperations;

    private ListOperations<String, Object> listOperations;

    public RedisUtil(RedisTemplate<String, Object>  redisTemplate,ValueOperations<String, Object> valueOperations, ListOperations<String, Object> listOperations) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = valueOperations;
        this.listOperations = listOperations;
    }

    /**
     * 生成自增序列号
     * @param prefix  前缀
     * @param numLength  要生成多少位的数字
     * @param expire 过期时长，单位：秒 -1不设置
     */
    public String SeqGenerator(String prefix, int numLength, long expire) {
        //如果前缀存在则不设置，前缀不存在就设置
        valueOperations.setIfAbsent(prefix,1);
        Integer upperCode = (Integer) valueOperations.get(prefix);
        valueOperations.increment(prefix,1);
        if (expire != NOT_EXPIRE){
            redisTemplate.expire(prefix,expire, TimeUnit.SECONDS);
        }
        log.info("获取前缀参数：[{}],从redis中取出为：[{}],设置时长为：[{}]",prefix,upperCode,expire);

        return prefix+String.format("%0"+numLength+"d",upperCode);
    }


    /**
     * 通过prefix存入redis数据，每次自增
     * @param prefix  前缀标识
     * @param listSize 数组长度
     * @return 角标
     */
    public Integer  saveValueAndIncrementByRoundRobinListSize(String prefix,int listSize){
        //如果前缀存在则不设置，前缀不存在就设置
        valueOperations.setIfAbsent(prefix,0);

        Integer result = (Integer) valueOperations.get(prefix);

        if (result >= listSize) {
            valueOperations.set(prefix, 0);
            result = 0;
        }else {
            valueOperations.increment(prefix,1);
        }

        return result;
    }

    public void set(String key, Object value, long expire) {
        valueOperations.set(key, toJson(value));
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = (String) valueOperations.get(key);
        if (expire != NOT_EXPIRE) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }



    /**
     * Object转成JSON数据
     */
    private String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return gson.toJson(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    /**
     * 获取所有list
     * @param key listKey
     */
    public List<Object> lGet(String key){
        return lGet(key,0,-1);
    }

    /**
     * 存 list
     * @param key key
     * @param data data
     */
    public void lSet(String key,List data){
        lSet(key,data,DEFAULT_EXPIRE);
    }

    /**
     * 获取list缓存的内容
     * @param key key 键
     * @param start 开始
     * @param end 结束  0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key,long start, long end){
        return listOperations.range(key,start,end);
    }


    private void lSet(String key, List<Object> data, long expire){
        listOperations.rightPushAll(key,data);
        if (expire != NOT_EXPIRE) expire(key,expire);
    }

    private void expire(String key, long expire) {
        redisTemplate.expire(key,expire,TimeUnit.SECONDS);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
