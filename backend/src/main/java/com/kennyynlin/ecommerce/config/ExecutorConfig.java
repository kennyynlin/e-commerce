package com.kennyynlin.ecommerce.config;

import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Configuration
public class ExecutorConfig {
    private ExecutorService executorService;

    @Value("${executor.thread.count}")
    private int threadCount;

    @Value("${executor.timeout.seconds}")
    private int timeoutInSeconds;

    private static final Logger logger = LoggerFactory.getLogger(ExecutorConfig.class);



    @Bean
    public ExecutorService executorService() {
        this.executorService = Executors.newFixedThreadPool(threadCount);
        return this.executorService;
    }

    @PreDestroy
    public void shutdownExecutorService() {
        logger.info("Trying to shutdown ExecutorService gracefully");
        if (executorService != null) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(timeoutInSeconds, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                    if (!executorService.awaitTermination(timeoutInSeconds, TimeUnit.SECONDS)) {
                        logger.error("ExecutorService did not terminate");
                    }
                }
                logger.info("ExecutorService shutdown completed gracefully");
            } catch (InterruptedException ie) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
                logger.info("ExecutorService shutdown by interruption");
            }
        }
    }
}
