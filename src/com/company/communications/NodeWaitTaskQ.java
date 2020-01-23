package com.company.communications;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Observer;
import java.util.Queue;

public class NodeWaitTaskQ extends Thread implements Observer, Observable {
    private static Queue<Socket> clientsSocketQueue = new LinkedList<>();
    private static int maxNumberOfWaitingSocket = 100;


    public static void addClient(Socket s) {
        if (clientsSocketQueue.size() < maxNumberOfWaitingSocket)
            clientsSocketQueue.add(s);
    }

    private Socket getClientSocket() {
        if (isThereClient())
            return clientsSocketQueue.poll();
        return null;
    }

    private boolean isThereClient() {
        return !clientsSocketQueue.isEmpty();
    }

    public void run() {
        while (!clientsSocketQueue.isEmpty()) {
            try {
                new uploadBlockRequestHandler(clientsSocketQueue.poll()).run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(java.util.Observable o, Object arg) {

    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}
