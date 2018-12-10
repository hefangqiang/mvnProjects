package com.redisLock;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;

import java.io.IOException;

/**
 * @description： TODO
 * @author： Mr.He
 * @date： 2018-12-01 15:38
 **/
public class Redis {
    public static void main(String[] args) throws IOException {
         ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
        //RedisTemplate redisTemplate = context.getBean(RedisTemplate.class);
        //System.out.println(redisTemplate);
       // redisTemplate..get("sex");
        //RedisTemplate srt = context.getBean(StringRedisTemplate.class);
      // System.out.println(srt.opsForValue().get("product"));
       // System.out.println(srt.opsForValue().get("test43143"));
        // srt.opsForValue().set("test43143","redisLock");
        //D:\Development\IdeaProjects\mvnProjects\mvn-redisLock\src\main\resources\lua\redisLock.lua
//       String s= FileUtils.readFileToString(new File("mvn-redisLock/src/main/resources/lua/redisLock.lua"),"UTF-8");
//       System.out.println(s);
        Jedis j1=new Jedis("127.0.0.1",6379);
        Jedis j2=new Jedis("127.0.0.1",6379);
    }

    @Test
    public void read() {
        System.out.println(this.getClass().getResourceAsStream("/lua/redisLock.lua").toString());
        System.out.println(this.getClass().getResource("/"));
        //Class.getResource("");
    }

}
