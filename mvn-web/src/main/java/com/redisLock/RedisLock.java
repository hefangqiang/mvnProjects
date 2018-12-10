package com.redisLock;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.io.File;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @description： TODO
 * @author： Mr.He
 * @date： 2018-12-02 15:43
 **/
@Service("redisLock")
public class RedisLock implements Lock {

    private final static String KEY="REDIS_LOCK";

    @Autowired
    @Qualifier("jedisConnectionFactory")
    private JedisConnectionFactory jedisConnectionFactory;

    private ThreadLocal<String> local=new ThreadLocal<String>();

    @Override
    public void lock() {
      //尝试加锁
        if (tryLock()) {
            return ;
        }
        //加锁失败 休眠一会
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //递归调用自身
        lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        String uuid="REDIS_LOCK_"+UUID.randomUUID().toString();
        //获得redis 原始连接
        JedisCluster jedisCluster= (JedisCluster) jedisConnectionFactory.getConnection().getNativeConnection();
        //使用setNX 设置值，失效时间
        String rset=jedisCluster.set(KEY,uuid,"NX","PX",5000);
        //rset 为  ok  则设置成功
        if ("OK".equals(rset)) {
            local.set(uuid);
            return true;
        }
        //加锁失败
        return false;
    }

    @Override
    public void unlock() {

        try {
            //读取lua脚本
            String lua=FileUtils.readFileToString(new File("src/main/resources/lua/redisLock.lua"),"UTF-8");
            //获得redis 原始连接
            JedisCluster jedisCluster= (JedisCluster) jedisConnectionFactory.getConnection().getNativeConnection();
            //执行lua脚本
            jedisCluster.eval(lua, Arrays.asList(KEY),Arrays.asList(local.get()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
