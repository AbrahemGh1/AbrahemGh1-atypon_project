package com.company.communications;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class uploadBlockRequestHandler {
    Socket clientSocket = null;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    private static InputSplitsHub hub;//this need to refactor


    public static void setE(InputSplitsHub hub) {
        uploadBlockRequestHandler.hub = hub;
    }


    uploadBlockRequestHandler(Socket s) throws IOException {
        clientSocket = s;
        dataInputStream = new DataInputStream(s.getInputStream());
        dataOutputStream = new DataOutputStream(s.getOutputStream());
    }

    //@Override
    public void run() {
        try {
            String a = dataInputStream.readUTF();
            while ("M".equals(a)) {
                hub.getNewInputSplit().write(dataOutputStream);
            }
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
