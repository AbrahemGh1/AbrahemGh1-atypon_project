package com.company.Mapper;

import com.company.input.SplitBlockInfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MapperListener extends Thread {
    static int count = 0;
    Socket s;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    static int p = 0;
    public List<SplitBlockInfo> ml = new ArrayList<>();
    public int TNumber = 0;

    public MapperListener(int portNumber) throws IOException {
        s = new Socket(InetAddress.getLocalHost(), portNumber);
        dataInputStream = new DataInputStream(s.getInputStream());
        dataOutputStream = new DataOutputStream(s.getOutputStream());
        TNumber = p;
        p++;
    }

    public void run() {
        try {
            dataOutputStream.writeUTF("REQUEST_TASK");
            dataOutputStream.writeInt(5);
            while (count < 5) {
                SplitBlockInfo s = new SplitBlockInfo();
                s.read(dataInputStream);
                ml.add(s);
                count++;
                System.out.println(count);
            }
            System.out.println("received Task:" + count);
        } catch (IOException o) {
            o.printStackTrace();
        }
    }
}
