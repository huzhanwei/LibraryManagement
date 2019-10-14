package com.hu.libraryManagement.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async("threadExecutor") //threadExecutor.java中的方法名，表明executeAsync方法进入的线程池是配置类AsyncConfig中threadExecutor方法创建的
    public void executeAsync() {  //线程
        System.out.println("当前线程名："+Thread.currentThread().getName());
    }
}
