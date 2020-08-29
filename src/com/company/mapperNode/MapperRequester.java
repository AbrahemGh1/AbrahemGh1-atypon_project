package com.company.mapperNode;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class MapperRequester extends Thread {
    private final Socket s;
    private String requestType;

    public MapperRequester(int portNumber, String requestName) throws IOException {
        s = new Socket(InetAddress.getLocalHost(), portNumber);
        this.requestType = requestName;
    }

    @Override
    public void run() {
        RequestHandler.runOnRequestMatch(s, requestType);
    }
}

