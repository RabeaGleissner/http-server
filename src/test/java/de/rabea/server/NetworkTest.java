package de.rabea.server;

import de.rabea.exceptions.SocketException;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NetworkTest {

    @Test
    public void readsOneLineOfInputForSimpleGETRequest() {
        String request = "GET/form HTTP/1.1\n";
        SocketStub socketStub = new SocketStub(request);
        Network network = new Network(socketStub);
        assertEquals("GET/form HTTP/1.1\n", network.read());
    }

    @Test
    public void readsBodyOfPostRequest() {
        String request = "POST /form HTTP/1.1\n" +
                "Content-Length: 11\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n" +
                "\n" +
                "data=fatcat";
        Network network = new Network(new SocketStub(request));
        String[] parsedRequestLines = network.read().split("\n");
        String lastLine = parsedRequestLines[parsedRequestLines.length -1];
        assertEquals("data=fatcat", lastLine);
    }

    @Test
    public void readsPutRequestWithoutBody() {
        String request = "PUT /method_options HTTP/1.1\n" +
                "Content-Length: 0\n" +
                "Host: localhost:5000\n" +
                "Connection: Keep-Alive\n" +
                "User-Agent: Apache-HttpClient/4.3.5 (java 1.5)\n" +
                "Accept-Encoding: gzip,deflate\n";
        Network network = new Network(new SocketStub(request));
        assertEquals(request, network.read());
    }

    @Test
    public void sendsHeaderAndBodyToClient() {
        SocketStub socketStub = new SocketStub();
        Network network = new Network(socketStub);
        String message = "hello!";
        network.write(message, "body".getBytes());
        assertEquals("hello!body", socketStub.messageSent());
    }

    @Test
    public void closesSocket() {
        SocketSpy socketSpy = new SocketSpy();
        Network network = new Network(socketSpy);
        network.close();
        assertTrue(socketSpy.socketWasClosed);
    }

    public class SocketSpy extends SocketStub {
        public boolean socketWasClosed = false;

        public void close() {
            socketWasClosed = true;
        }
    }

    @Test(expected = SocketException.class)
    public void socketThrowsIOExceptionWhenItCannotClose() {
        SocketWithException socket = new SocketWithException().throwExceptionForClose();
        Network network = new Network(socket);
        network.close();
    }

    @Test(expected = SocketException.class)
    public void throwsBufferedReaderException() {
        SocketWithException socket = new SocketWithException().throwExceptionForInputStream();
        Network network = new Network(socket);
        network.createReader();
    }

    @Test(expected = SocketException.class)
    public void throwsSocketExceptionWhenItCannotGetOutputStream() {
        SocketWithException socket = new SocketWithException().throwExceptionForOutputStream();
        Network network = new Network(socket);
        network.createSender();
    }

    public class SocketWithException extends Socket {

        private boolean inputStreamException;
        private boolean outputStreamException;
        private boolean exceptionForClose;

        public SocketWithException() {
        }

        public SocketWithException(boolean closeException, boolean inputStreamException, boolean outputStreamException) {
            this.exceptionForClose = closeException;
            this.inputStreamException = inputStreamException;
            this.outputStreamException = outputStreamException;
        }

        public SocketWithException throwExceptionForClose() {
            exceptionForClose = true;
            inputStreamException = false;
            outputStreamException = false;
            return new SocketWithException(exceptionForClose, inputStreamException, outputStreamException);
        }

        public SocketWithException throwExceptionForOutputStream() {
            exceptionForClose = false;
            inputStreamException = false;
            outputStreamException = true;
            return new SocketWithException(exceptionForClose, inputStreamException, outputStreamException);
        }

        public SocketWithException throwExceptionForInputStream() {
            exceptionForClose = false;
            inputStreamException = true;
            outputStreamException = false;
            return new SocketWithException(exceptionForClose, inputStreamException, outputStreamException);
        }

        @Override
        public void close() throws IOException {
            if (exceptionForClose) {
                throw new IOException();
            }
        }

        @Override
        public InputStream getInputStream() throws IOException {
            if (inputStreamException) {
                throw new IOException();
            }
            return new ByteArrayInputStream("".getBytes()) {
            };
        }

        @Override
        public OutputStream getOutputStream() throws IOException {
            if (outputStreamException) {
                throw new IOException();
            }
            return new ByteArrayOutputStream();
        }
    }
}