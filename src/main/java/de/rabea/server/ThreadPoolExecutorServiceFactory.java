package de.rabea.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorServiceFactory implements ExecutorServiceFactory {

    @Override
    public ExecutorService create(int threadAmount) {
        return Executors.newFixedThreadPool(threadAmount);
    }
}
