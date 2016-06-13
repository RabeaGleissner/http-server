package de.rabea.server;

import org.junit.Test;

import java.util.concurrent.ExecutorService;

import static org.junit.Assert.*;

public class ThreadPoolExecutorServiceFactoryTest {

    @Test
    public void createsNewExecutorService() {
        int threadCount = 1;
        ThreadPoolExecutorServiceFactory factory = new ThreadPoolExecutorServiceFactory();
        assertTrue(factory.create(threadCount) instanceof ExecutorService);
    }
}