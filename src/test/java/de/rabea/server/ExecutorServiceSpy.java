package de.rabea.server;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

class ExecutorServiceSpy extends AbstractExecutorService {

    public boolean executesHttpServerRunner = false;
    public boolean hasShutDown = false;

    @Override
    public void execute(Runnable command) {
        executesHttpServerRunner = true;
    }

    @Override
    public void shutdown() {
        hasShutDown = true;
    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }
}
