package com.company.comm;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileDownloaderListener extends Thread implements Stoppable {
    private final int portNumber;
    private volatile boolean cancelled = false;
    private ServerSocket serverSocket;

    public FileDownloaderListener(int portNumber) throws IOException {
        this.portNumber = portNumber;
        serverSocket = new ServerSocket(portNumber);
    }

    public int getPortNumber() {
        return portNumber;
    }


    public void run() {
        while (!cancelled) {
            try (Socket s = serverSocket.accept()) {
                System.out.println("accepted");
                new FileDownloader(s).startDownloadFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void Stop() {
        this.interrupt();
        cancelled = true;
    }
}



