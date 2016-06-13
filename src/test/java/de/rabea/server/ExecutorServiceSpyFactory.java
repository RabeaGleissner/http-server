package de.rabea.server;

import java.util.concurrent.*;

public class ExecutorServiceSpyFactory implements ExecutorServiceFactory {

    private ExecutorServiceSpy executorServiceSpy;

    public ExecutorServiceSpyFactory(ExecutorServiceSpy executorServiceSpy) {
        this.executorServiceSpy = executorServiceSpy;
    }

    @Override
    public ExecutorService create(int amount) {
        return executorServiceSpy;
    }

}
