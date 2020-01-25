package com.company.managerNode;

import com.company.comm.Stoppable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ManagerListener extends Thread implements Stoppable {
    static boolean x = true;
    private ServerSocket serverSocket;
    private int portNumber;

    public ManagerListener(int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.portNumber = portNumber;
    }

    //@Override
    public void run() {
        System.out.println("ManagerListener Listen to port:" + portNumber);
        while (true) {
            try {
                Socket s = serverSocket.accept();
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                DataInputStream in = new DataInputStream(s.getInputStream());
                System.out.println("accepted");
                String messageCommand = in.readUTF();
                RequestCommand.valueOf(messageCommand).execute(s);
                //    ExecutorService service = new ThreadPoolExecutor(8,8, more args here...);
                System.out.println("client disc");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void Stop() {

    }
}


