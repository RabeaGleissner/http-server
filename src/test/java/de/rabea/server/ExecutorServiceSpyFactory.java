package de.rabea.server;

import java.util.concurrent.ExecutorService;

public class ExecutorServiceSpyFactory implements ExecutorServiceFactory {

    private final ExecutorServiceSpy executorServiceSpy;

    public ExecutorServiceSpyFactory(ExecutorServiceSpy executorServiceSpy) {
        this.executorServiceSpy = executorServiceSpy;
    }

    @Override
    public ExecutorService create(int amount) {
        return executorServiceSpy;
    }

}
