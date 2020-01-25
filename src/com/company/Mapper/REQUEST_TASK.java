package com.company.Mapper;

import com.company.input.SplitBlockInfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class REQUEST_TASK implements Requester {
    public List<SplitBlockInfo> TasksList = new ArrayList<>();
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    Socket socket;

    REQUEST_TASK(Socket s) {
        try {
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
            System.out.println(count);
        }
    }

    private void receiveTaskFromSocket() throws IOException {
        SplitBlockInfo sb = new SplitBlockInfo();
        sb.read(dataInputStream);
        TasksList.add(sb);
    }
}
