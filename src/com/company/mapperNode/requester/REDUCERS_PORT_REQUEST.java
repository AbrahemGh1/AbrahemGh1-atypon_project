package com.company.mapperNode.requester;

import com.company.mapperNode.requester.Requester;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

public class REDUCERS_PORT_REQUEST implements Requester {
    private static HashMap<Integer, Integer> reducersNode = new HashMap<>();
    private final String RequestType = "REQUEST_REDUCERS_PORT";
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public static HashMap<Integer, Integer> getReducerNodeAsHashMap() {
        return reducersNode;
    }


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
                reducersNode.put(ReducersNumber, Port);
                ReducersNumber++;
            }
        } catch (IOException ignore) {
            //connection normally closed
        }
    }
}
