package com.company.reducer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket s = null;
        try {
            s = new ServerSocket(4000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            Socket mapper=s.accept();
            DataInputStream dataInputStream= new DataInputStream(mapper.getInputStream());
            DataOutputStream dataOutputStream= new DataOutputStream(mapper.getOutputStream());
            System.out.println("accepted in reducer");
            while (true) ;
        }

    }
}
