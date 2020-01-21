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
    public static List<SplitBlockInfo> ml = new ArrayList();

    public MapperListener(int portNumber) throws IOException {
        s = new Socket(InetAddress.getLocalHost(), portNumber);
        dataInputStream = new DataInputStream(s.getInputStream());
        dataOutputStream = new DataOutputStream(s.getOutputStream());
    }

    public void run() {
        try {
            dataOutputStream.writeUTF("M");
            while (count < 19113) {
                SplitBlockInfo s = new SplitBlockInfo();
                s.readFields(dataInputStream);
                ml.add(s);
                count++;
                System.out.println(count);
                dataOutputStream.writeUTF("M");
            }
        } catch (IOException o) {
            o.printStackTrace();
        }
    }
}
