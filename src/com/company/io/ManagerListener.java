package com.company.io;

import com.company.comm.Stoppable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ManagerListener extends Thread implements Stoppable {
    private ServerSocket serverSocket;

    public ManagerListener(int portNumber) throws IOException {
        serverSocket = new ServerSocket(portNumber);
    }

    public void Stop() {

    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket s = serverSocket.accept();
                System.out.println("accepted");
                new uploadBlockRequestHandler(s).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


