package com.example.demo.test.lock;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Share {
    private int num = 0;

    //创建lock
    Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //+1
    public void incr() throws InterruptedException {
        lock.lock();//上锁
        try {
            //判断
            while (num != 0) { //不等于0就等待
                condition.await();
            }
            //干活
            num++;
            System.out.println(Thread.currentThread().getName() + "::" + num);
            //通知
            condition.signalAll();//通知其他线程
        } finally {
            //解锁
            lock.unlock();
        }
    }

    //-1
    public void decr() throws InterruptedException {
        lock.lock();//上锁
        try {
            //判断
            while (num != 1) { //不等于0就等待
                condition.await();
            }
            //干活
            num--;
            System.out.println(Thread.currentThread().getName() + "::" + num);
            //通知
            condition.signalAll();//通知其他线程
        } finally {
            //解锁
            lock.unlock();

        }
    }

}

public class ThreadDemo {
    public static void main(String[] args) {
        Share share = new Share();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "aa").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "bb").start();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "cc").start();


        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "dd").start();
    }
}
