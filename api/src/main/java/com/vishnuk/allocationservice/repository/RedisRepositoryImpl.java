package com.vishnuk.allocationservice.repository;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Repository
public class RedisRepositoryImpl implements RedisRepository {

    private RedisTemplate<String, String> redisTemplate;
    private ValueOperations<String, String> valueOperations;
    private SetOperations<String, String> setOperations;
    private HashOperations<String, String, String> hashOperations;
    private ListOperations<String, String> listOperations;

    @Autowired
    public RedisRepositoryImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        valueOperations = redisTemplate.opsForValue();
        setOperations = redisTemplate.opsForSet();
        hashOperations=redisTemplate.opsForHash();
        listOperations=redisTemplate.opsForList();
    }


    @Override
    public String save(String key, String value) {
        valueOperations.set(key, value);
        return key;
    }

    @Override
    public String saveSetEntries(String key, String value) {
         setOperations.add(key, value);
        return key;
    }

    @Override
    public String saveHashEntries(String key, String hashKey, String hashValue) {
        hashOperations.put(key, hashKey, hashValue);
        return key;
    }


    @Override
    public String saveAndExpiry(String key, String value, long expiryTimeInSeconds) {
        valueOperations.set(key, value, expiryTimeInSeconds, TimeUnit.SECONDS);

        return key;
    }


    @Override
    public String find(String key){
        return valueOperations.get(key);
    }

    @Override
    public Set<String> findSetEntries(String key){
        Set<String> res=null;
        res= setOperations.members(key);
        return res;
    }

    @Override
    public Map<String, String> findHashEntries(String redisKey) {
        Map<String, String> result=hashOperations.entries(redisKey);
        return result;

    }
    @Override
    public String saveListEntries(String key, String value) {
        listOperations.leftPush(key, value);
        return key;
    }

    @Override
    public List<String> findListEntries(String key){
        List<String> res=null;
        res=listOperations.range(key, 0L, -1L);
        return res;
    }

}
