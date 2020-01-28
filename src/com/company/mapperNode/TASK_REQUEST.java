package com.company.mapperNode;

import com.company.input.InputBlock;
import com.company.mapperNode.requester.Requester;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;

class TASK_REQUEST extends Observable implements Requester {
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    Socket socket;

    TASK_REQUEST(Socket s) {
        try {
            this.addObserver(Main.taskExecutor);
            socket = s;
            dataInputStream = new DataInputStream(s.getInputStream());
            dataOutputStream = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void request(Socket s) {
        try {
            String REQUEST_TASK = "REQUEST_TASK";
            dataOutputStream.writeUTF(REQUEST_TASK);
            while (true) {
                receiveTaskFromSocket();
            }
        } catch (IOException ignore) {
            Main.taskExecutor.getResult();
        }
    }

    private void receiveTaskFromSocket() throws IOException {
        InputBlock sb = new InputBlock();
        sb.read(dataInputStream);
        setChanged();
        notifyObservers(sb);
    }
}
