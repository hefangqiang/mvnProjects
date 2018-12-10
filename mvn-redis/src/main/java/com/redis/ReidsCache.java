package com.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StringUtils;

import java.util.concurrent.Callable;

/**
 * @description： TODO
 * @author： Mr.He
 * @date： 2018-12-08 15:34
 **/
@Component
public class ReidsCache implements Cache {

    private  String name;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void setName(String name) {
        this.name = name;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.redisTemplate;
    }

    @Override
    public ValueWrapper get(Object key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }else {
            final String finalKey;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            final Object object;
            object = redisTemplate.execute((RedisCallback<Object>) redisConnection -> {
                byte[] keyBytes = finalKey.getBytes();
                byte[] valueBytes = redisConnection.get(keyBytes);
                if (valueBytes == null) {
                    return null;
                }
                return SerializationUtils.deserialize(valueBytes);
            });
            return (object != null ? new SimpleValueWrapper(object) : null);
        }
    }

    @Override
    public <T> T get(Object key, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return;
        } else {
            final String finalKey;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            redisTemplate.execute((RedisCallback<Object>) redisConnection -> {
                redisConnection.set(finalKey.getBytes(), SerializationUtils.serialize(value));
                return true;
            });
        }


    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public void evict(Object key) {
        if (StringUtils.isEmpty(key)) {
            return;
        } else {
            final String finalKey;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            redisTemplate.execute((RedisCallback<Object>) redisConnection -> {
                redisConnection.del(finalKey.getBytes());
                return true;
            });
        }
    }

    @Override
    public void clear() {

    }
}
