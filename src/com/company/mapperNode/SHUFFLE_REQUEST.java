package com.company.mapperNode;

import com.company.mapperNode.requester.Requester;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.Map;

public class SHUFFLE_REQUEST implements Requester {
    private DataInputStream in = null;
    private DataOutputStream socketOut = null;
    private final String INPUT_TO_REDUCERS = "INPUT_TO_REDUCERS";

    private static Map<Integer, List<Map.Entry>> MapperNodeOutput = null;

    public static void setMapperNodeOutput(Map<Integer, List<Map.Entry>> mapperOut) {
        MapperNodeOutput = mapperOut;
    }


    public SHUFFLE_REQUEST(Socket socket) {
        try {
            in = new DataInputStream(socket.getInputStream());
            socketOut = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void request(Socket s) {
        try {
            socketOut.writeUTF(INPUT_TO_REDUCERS);
            List<Map.Entry> temp = MapperNodeOutput.get(0);//i need to find way to get the index of key in MapperNodeOutput where the key represant the Reducer Node.
            for (int i = 0; i < temp.size(); i++) {
                Map.Entry entry = temp.get(i);
                socketOut.writeUTF((String) entry.getKey());
                socketOut.writeInt((Integer) entry.getValue());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

