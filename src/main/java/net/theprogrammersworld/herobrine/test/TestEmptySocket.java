package net.theprogrammersworld.herobrine.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public final class TestEmptySocket extends Socket {

    private static final byte[] EMPTY = new byte[50];

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(TestEmptySocket.EMPTY);
    }

    @Override
    public OutputStream getOutputStream() {
        return new ByteArrayOutputStream(10);
    }
}