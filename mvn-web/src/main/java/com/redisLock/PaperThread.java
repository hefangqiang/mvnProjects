package com.redisLock;

import java.util.concurrent.locks.Lock;

/**
 * @description： TODO
 * @author： Mr.He
 * @date： 2018-12-01 10:54
 **/
public class PaperThread implements Runnable {
    private Lock lock;
    private int count;

    public PaperThread( Lock lock, int count) {
        this.lock=lock;
        this.count=count;
    }

    @Override
    public void run() {
        while (count>0){
            lock.lock();
            try {
                if (count>0) {
                    System.out.println(Thread.currentThread().getName()+"卖出第"+count--+"张票");
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                lock.unlock();
            }

        }
    }
}
