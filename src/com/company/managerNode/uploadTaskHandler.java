package com.company.managerNode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class uploadTaskHandler extends Thread {
    private static TaskPool taskPool;//this need to refactor
    Socket clientSocket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;


    uploadTaskHandler(Socket s) throws IOException {
        clientSocket = s;
        dataInputStream = new DataInputStream(s.getInputStream());
        dataOutputStream = new DataOutputStream(s.getOutputStream());
    }

    public static void setE(TaskPool pool) {
        uploadTaskHandler.taskPool = pool;
    }

    @Override
    public void run() {
        try {
            uploadTasks();
        } catch (TasksFinishedBeforeFullyServed ignore) {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println("taskPool closed");
            e.printStackTrace();
        }
    }


    private void uploadTasks() throws IOException {
        while (true) {
            uploadTask();
        }
    }

    private void uploadTask() throws IOException {
        synchronized (taskPool) {
            if (taskPool.isEmpty())//review function name
                throw new TasksFinishedBeforeFullyServed();
            taskPool.getNewInputSplit().write(dataOutputStream);
        }
    }
}

