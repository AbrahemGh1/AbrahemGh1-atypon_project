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
    private static InputSplitsHub TasksHub;//this need to refactor


    public static void setE(InputSplitsHub hub) {
        uploadBlockRequestHandler.TasksHub = hub;
    }


    uploadBlockRequestHandler(Socket s) throws IOException {
        clientSocket = s;
        dataInputStream = new DataInputStream(s.getInputStream());
        dataOutputStream = new DataOutputStream(s.getOutputStream());
    }

    //@Override
    public void run() {
        try {
            String messageFromClient = dataInputStream.readUTF();
            switch (messageFromClient) {
                case "M":
                    clientRequestInputSplit(dataInputStream);
                    break;
                case "done":
            }
            messageFromClient = dataInputStream.readUTF();
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void clientRequestInputSplit(DataInputStream dataInputStream) throws IOException {
        do {
            TasksHub.getNewInputSplit().write(dataOutputStream);
        } while (dataInputStream.readUTF().equalsIgnoreCase("M") && TasksHub.hasInputSplit());
    }

}
