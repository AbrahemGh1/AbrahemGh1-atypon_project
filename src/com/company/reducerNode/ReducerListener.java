package com.company.reducerNode;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ReducerListener extends Thread{

    DataInputStream in;
    private ServerSocket listenerSocket;
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    private volatile boolean runningState;
    int portNumber;

    ReducerListener(int portNumber) {
        runningState = true;
        this.portNumber=portNumber;
        try {
            listenerSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("Reducer start at port:"+portNumber);
        while (runningState) {

            try {
                Socket acceptedSocket = listenerSocket.accept();
                in = new DataInputStream(acceptedSocket.getInputStream());
                System.out.println("accepted connection:");
                String request = in.readUTF();
                System.out.println(request);
                switch (request) {
                    case "InputToReducer":
                        new MapperOutputHandler(acceptedSocket).start();
                        break;
                    default:
                        System.out.println("can't handler request");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean shutdownExecutorService() {

        try {
            executorService.awaitTermination(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return executorService.isTerminated();
    }

    public void shutdown() {
        executorService.shutdown();
    }
}

class MapperOutputHandler {
    private DataInputStream inputStream;
    private static final Map<Object, Object> MapperOut = Collections.synchronizedMap(new TreeMap<>());


    public static Map<Object, Object> getMapperOut() {
        return MapperOut;
    }

    MapperOutputHandler(Socket mapperSocket) {
        try {
            inputStream = new DataInputStream(mapperSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void start() {
        String mapperMessage;
        try {
            do {
                String key = inputStream.readUTF();
                Integer value = inputStream.readInt();
                System.out.println(key + "," + value);
                if (MapperOut.containsKey(key)) {
                    MapperOut.put(key, (Integer) MapperOut.get(key) + value);
                } else {
                    MapperOut.put(key, value);
                }
                //               mapperMessage = inputStream.readUTF();
            } while (true);
        } catch (IOException ignore) {
            //this mean the connection close by mapper
        }
    }
}