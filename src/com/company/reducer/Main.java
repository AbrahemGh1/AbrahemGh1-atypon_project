package com.company.reducer;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket s = null;
        try {
            s = new ServerSocket(3000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            s.accept();
            System.out.println("accepted");
            while (true) ;
        }

    }
}
