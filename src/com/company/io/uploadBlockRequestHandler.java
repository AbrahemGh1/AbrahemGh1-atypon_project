package com.company.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class uploadBlockRequestHandler extends Thread {
    Socket clientSocket = null;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    InputSplitHub e = new InputSplitHub();//this need to refactor


    uploadBlockRequestHandler(Socket s) throws IOException {
        clientSocket = s;
        dataInputStream = new DataInputStream(s.getInputStream());
        dataOutputStream = new DataOutputStream(s.getOutputStream());
    }

    @Override
    public void run() {
        try {
            String a = dataInputStream.readUTF();
            while ("M".equals(a)) {
                e.getNewInputSplit().write(dataOutputStream);
            }
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
