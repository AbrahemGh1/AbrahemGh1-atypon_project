package com.company.communications;

import com.company.comm.Stoppable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

enum RequestCommand implements Command {

    REQUEST_TASK {
        @Override
        public void execute(Socket s) throws IOException {
            new uploadBlockRequestHandler(s).run();
        }
    },

    REQUEST_CLOSE {
        @Override
        public void execute(Socket s) throws IOException {
            s.close();
        }
    }
}

interface Command {
    void execute(Socket s) throws IOException;
}

public class ManagerListener /*extends Thread*/ implements Stoppable {
    private ServerSocket serverSocket;
    private int portNumber;

    public ManagerListener(int portNumber) throws IOException {
        serverSocket = new ServerSocket(portNumber);
        this.portNumber = portNumber;
    }

    //@Override
    public void run() {
        System.out.println("ManagerListener Listen to port:" + portNumber);
        try (Socket s = serverSocket.accept();
             DataOutputStream out = new DataOutputStream(s.getOutputStream());
             DataInputStream in = new DataInputStream(s.getInputStream())) {

            String messageCommand = in.readUTF();
            RequestCommand.valueOf(messageCommand).execute(s);
            //    ExecutorService service = new ThreadPoolExecutor(8,8, more args here...);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Stop() {

    }
}


