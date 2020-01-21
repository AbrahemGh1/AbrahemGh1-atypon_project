package com.company.communications;

import com.company.comm.Stoppable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ManagerListener implements Stoppable {
    private ServerSocket serverSocket;
    private int portNumber;

    public ManagerListener(int portNumber) throws IOException {
        serverSocket = new ServerSocket(portNumber);
        this.portNumber = portNumber;
    }

    public void Stop() {

    }

    //@Override
    public void run() {
        System.out.println("ManagerListener Listen to port:" + portNumber);
        try {
            while (true) {
                Socket s = serverSocket.accept();
                System.out.println("accepted");
                new uploadBlockRequestHandler(s).run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


