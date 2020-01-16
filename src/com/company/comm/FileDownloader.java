package com.company.comm;

import java.io.*;
import java.net.Socket;

public class FileDownloader extends Thread{

    final Socket clientSocket;

    public FileDownloader(Socket s) {
        clientSocket = s;
    }

    @Override
    public void run() {
        startDownloadFile();
        this.interrupt();
    }

    public void startDownloadFile() {
        while (!clientSocket.isClosed()) {
            try (DataOutputStream out = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
                 DataInputStream in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream())))
            {
                byte[] bytes = new byte[1];
                FileOutputStream fos = new FileOutputStream("Config2.xml");
                while (in.read(bytes)!=-1){
                    fos.write(bytes);
                }
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
