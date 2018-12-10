package com.redisLock;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.locks.Lock;


/**
 * @description： TODO
 * @author： Mr.He
 * @date： 2018-12-01 11:07
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-*.xml"})
public class SoldPaperTest {

    private int count=100;

    @Autowired
    @Qualifier("redisLock")
    private Lock lock ;

    @Test
    public  void soldPaper() throws InterruptedException {

        System.out.println("ttttt");
        Runnable r1=new PaperThread(lock,count);
        Thread t1=new Thread(r1,"A");
        Thread t2=new Thread(r1,"B");
        Thread t3=new Thread(r1,"C");
        t1.start();
        t2.start();
        t3.start();

       Thread.currentThread().join();

    }

    @Test
    public void t1() {
        try {
            String lua= FileUtils.readFileToString(new File("src/main/resources/lua/redisLock.lua"),"UTF-8");
            System.out.println(lua);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
