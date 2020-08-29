package com.company.reducerNode;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ReducerMain {
    public static void main(String[] args) throws IOException {
        ReducerListener reducerListener = new ReducerListener(4000);
        reducerListener.start();
        reducerListener.shutdown();
        while (!reducerListener.shutdownExecutorService()) ;//wait to finish
        //reducerListener.interrupt();



        Map<Object, Object> reducerOutput = MapperOutputHandler.getMapperOut();

        if (true)//replace with arg[0] when run the node from shell and send 1 as flag if this the last reducer
        {
            BufferedWriter dst = new BufferedWriter(new FileWriter("output.txt"));
            reducerOutput.keySet().forEach(k -> {
                try {
                    dst.write(k + "," + reducerOutput.get(k) + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
