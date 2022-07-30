package com.example.demo.threadPool;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: ct
 * @CreateTime: 2022-07-08  16:30
 * @Description: TODO
 * @Version: 1.0
 */
public class Threadpool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor executorService1 = (ThreadPoolExecutor) executorService;
//        executorService1.setCorePoolSize();
//        executorService1.setKeepAliveTime();
//
//        executorService.submit(); //实现callable接口
//        executorService.execute();//实现runnable接口
        executorService.shutdown();//关闭线程池
    }
}
