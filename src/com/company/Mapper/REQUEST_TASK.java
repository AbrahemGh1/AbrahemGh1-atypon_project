package com.company.Mapper;

import com.company.input.InputBlock;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

class REQUEST_TASK extends Observable implements Requester {
    public static List<InputBlock> TasksList = new ArrayList<>();
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    Socket socket;

    REQUEST_TASK(Socket s) {
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
        int count = 0;
        try {
            String REQUEST_TASK = "REQUEST_TASK";
            dataOutputStream.writeUTF(REQUEST_TASK);
            while (true) {
                receiveTaskFromSocket();
                count++;
            }
        } catch (IOException ignore) {
            System.out.println("number Of input block for this mapper is:" + count);
            Main.taskExecutor.getResult();
        }
    }

    private void receiveTaskFromSocket() throws IOException {
        List e = new ArrayList();
        InputBlock sb = new InputBlock();
        sb.read(dataInputStream);
        TasksList.add(sb);
        boolean flag = e.add(sb);

        if (!flag) {
            System.exit(0);

        }
        setChanged();
        notifyObservers(sb);
    }
}
