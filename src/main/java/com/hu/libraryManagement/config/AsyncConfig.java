package com.hu.libraryManagement.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

//线程池配置
//SpringBoot线程池配置 https://blog.csdn.net/m0_37701381/article/details/81072774
//理解 ThreadPoolExecutor : https://blog.csdn.net/codertnt/article/details/78971506
//线程池的四种创建： https://blog.csdn.net/vigoss77/article/details/81842199
@Configuration
@EnableAsync
public class AsyncConfig {

    private static final Logger logger = LoggerFactory.getLogger(AsyncConfig.class);

    private int corePoolSize = 10;//线程池维护线程的最少数量
    private int maxPoolSize = 50;//线程池维护线程的最大数量
    private int queueCapacity = 20; //缓存队列
    private int keepAlive = 120;//允许的空闲时间
    @Bean
    public ThreadPoolTaskExecutor threadExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("threadExecutor-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); //对拒绝task的处理策略
        executor.setKeepAliveSeconds(keepAlive);
        executor.initialize();
        return executor;
    }

}
