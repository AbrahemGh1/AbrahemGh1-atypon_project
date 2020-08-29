package com.company.managerNode;

import com.company.comm.Stoppable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class ManagerListener extends Thread implements Stoppable {
    private ServerSocket serverSocket;
    private int portNumber;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public ManagerListener(int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.portNumber = portNumber;
    }

    @Override
    public void run() {
        System.out.println("ManagerListener Listen to port:" + portNumber);
        while (true) {
            try {
                Socket s = serverSocket.accept();
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                DataInputStream in = new DataInputStream(s.getInputStream());
                System.out.println("accepted");
                String messageCommand = in.readUTF();
                System.out.println(messageCommand);
                RequestCommand.valueOf(messageCommand).execute(s);
                System.out.println("Node disconnected");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void Stop() {
        interrupt();
        if (!isInterrupted()) {
            running.set(true);
        }
    }
}


