package com.example.demo.test.lock;

import lombok.SneakyThrows;
import sun.applet.Main;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Share1{
    //创建lock
    Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition(); //相当于三把钥匙
    private Condition condition2= lock.newCondition();
    private Condition condition3 = lock.newCondition();
    int flag=1;
    public void p5() throws InterruptedException {
        lock.lock();//上锁
        try {
            while (flag!=1){
                condition1.await();//等待
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i);
            }
            //修改标识符
            flag=2;
            condition2.signal();//通知下个线程
        }finally {
            //解锁
            lock.unlock();
        }
    }

    public void p10() throws InterruptedException {
        lock.lock();//上锁
        try {
            while (flag!=2){
                condition2.await();//等待
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i);
            }
            //修改标识符
            flag=3;
            condition3.signal();//通知下个线程
        }finally {
            //解锁
            lock.unlock();
        }
    }

    public void p15() throws InterruptedException {
        lock.lock();//上锁
        try {
            while (flag!=3){
                condition3.await();//等待
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"::"+i);
            }
            //修改标识符
            flag=1;
            condition1.signal();//通知下个线程
        }finally {
            //解锁
            lock.unlock();
        }
    }
}




public class ThreadDemo2 {

    public static void main(String[] args) {
        Share1 share1=new Share1();
        new Thread(()->{
            try {
                share1.p5();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"aa").start();

        new Thread(()->{
            try {
                share1.p10();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"bb").start();

        new Thread(()->{
            try {
                share1.p15();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"cc").start();
    }
}
