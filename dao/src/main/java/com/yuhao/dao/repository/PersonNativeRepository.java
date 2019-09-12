package com.yuhao.dao.repository;

import com.yuhao.dao.domain.vo.PersonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class PersonNativeRepository {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Resource(name="stringRedisTemplate")
    ValueOperations<String,String> valOpsStr;

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Resource(name="redisTemplate")
    ValueOperations<Object,Object> valOpsRedis;

    public void setString(String key,String value){
        valOpsStr.set(key,value);
    }

    public void setObject(String key,PersonVo personVo){
        valOpsRedis.set(key,personVo);
    }

    public String getString(String key){
        return valOpsStr.get(key);
    }

    public PersonVo getObject(String key){
        return (PersonVo)valOpsRedis.get(key);
    }
}
