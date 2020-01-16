package com.company.test;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;

public class fileUploader {
    Socket serverSocket;

    public fileUploader(String host, int portNumber) {
        try {
            serverSocket = new Socket(host, 2022);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadFile(Path p) {
        byte[] bytearray = new byte[1024 * 16];
        FileInputStream fis = null;
        try {
            File f = new File(String.valueOf(p));
            fis = new FileInputStream(String.valueOf(p));
            bytearray = new byte[(int) f.length()];
            OutputStream output = serverSocket.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(fis);

            int readLength = -1;
            while ((readLength = bis.read(bytearray)) > 0) {
                output.write(bytearray, 0, readLength);

            }
            bis.close();
            output.close();
            serverSocket.close();
        } catch (Exception ex) {

            ex.printStackTrace();
        }


    }

}