package com.company.Mapper;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class MapperListener extends Thread {
    private final Socket s;
    private String requestName;

    public MapperListener(int portNumber, String requestName) throws IOException {
        s = new Socket(InetAddress.getLocalHost(), portNumber);
        this.requestName = requestName;
    }

    public void run() {
        RequestHandler.runOnRequestMatch(s, requestName);
    }
}

