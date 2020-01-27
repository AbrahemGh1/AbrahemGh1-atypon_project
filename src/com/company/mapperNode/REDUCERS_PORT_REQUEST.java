package com.company.mapperNode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class REDUCERS_PORT_REQUEST implements Requester {
    public static HashMap<Integer, Integer> REDUCERS_NODE = new HashMap<>();
    private final String RequestType = "REQUEST_REDUCERS_PORT";
    private DataInputStream in = null;
    private DataOutputStream out = null;


    public REDUCERS_PORT_REQUEST(Socket socket) {
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void request(Socket s) {
        try {
            int ReducersNumber = 0;
            out.writeUTF(RequestType);
            while (!s.isClosed()) {
                int Port = in.readInt();
                System.out.println(Port);
                REDUCERS_NODE.put(ReducersNumber, Port);
                ReducersNumber++;
            }
        } catch (IOException ignore) {
            //connection Normally Closed
        }
    }
}
