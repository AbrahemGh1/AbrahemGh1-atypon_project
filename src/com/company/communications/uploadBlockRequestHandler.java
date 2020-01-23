package com.company.communications;

import com.company.input.SplitBlockInfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class uploadBlockRequestHandler {
    Socket clientSocket = null;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    private static TaskPool taskPool;//this need to refactor


    public static void setE(TaskPool pool) {
        uploadBlockRequestHandler.taskPool = pool;
    }

    uploadBlockRequestHandler(Socket s) throws IOException {
        clientSocket = s;
        dataInputStream = new DataInputStream(s.getInputStream());
        dataOutputStream = new DataOutputStream(s.getOutputStream());
    }

    //HasMore
    //@Override
    public void run() {
        try {
            int numberOfRequestedTasks = dataInputStream.readInt();
            uploadTask(numberOfRequestedTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadTask(int numberOfRequestedTasks) throws IOException {
        int count = 0;
        if (!taskPool.isClosed()) {//here u may need to add a synchronized
            while (taskPool.hasTask() && CheckIfClientNeedMore(count, numberOfRequestedTasks)) {
                taskPool.getNewInputSplit().write(dataOutputStream);
                count++;
            }
        }
    }

    private boolean CheckIfClientNeedMore(int count, int numberOfRequestedTasks) {
        return (count < numberOfRequestedTasks);
    }

    private void addNodeToWaitingQ(Socket clientSocket) {
    }

    private void requestNodeToShutDown() {
        throw new IllegalStateException("Request Node to close.");
    }

    private void uploadInputSplitToClient(SplitBlockInfo e) throws IOException {
        e.write(dataOutputStream);
    }
}

