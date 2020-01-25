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
            //  int numberOfRequestedTasks = dataInputStream.readInt();
            uploadTasks(0);
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


    private void uploadTasks(int numberOfRequestedTasks) throws IOException {
        int x = 0;
        while (true) {
            uploadTask();
            x++;
            System.out.println(x);
        }
    }

    private void uploadTask() throws IOException {
        synchronized (taskPool) {
            if (!taskPool.tasksFinished())
                throw new TasksFinishedBeforeFullyServed();
            taskPool.getNewInputSplit().write(dataOutputStream);
        }
    }
}

