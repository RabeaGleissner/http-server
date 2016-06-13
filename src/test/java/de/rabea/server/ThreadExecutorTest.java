package de.rabea.server;

import de.rabea.request.Log;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class ThreadExecutorTest {
    private ExecutorServiceSpy executorServiceSpy;
    private ThreadExecutor threadExecutor;

    @Before
    public void setup() throws IOException {
        executorServiceSpy = new ExecutorServiceSpy();
        threadExecutor = new ThreadExecutor(new ExecutorServiceSpyFactory(executorServiceSpy),
                new ServerSocketStub(), new ContentStorage(), new Log(), "PUBLIC_DIR");
    }

    @Test
    public void executesHttpServerRunner() throws IOException {
        threadExecutor.executeServerRunnerInThread();
        assertTrue(executorServiceSpy.executesHttpServerRunner);
    }

    @Test
    public void executorServiceShutsDown() throws IOException {
        threadExecutor.shutdown();
        assertTrue(executorServiceSpy.hasShutDown);
    }
}