package com.company.Mapper;

import com.company.input.InputBlock;

import java.io.IOException;
import java.util.HashMap;

public abstract class Mapper<keyIn, valueIn, keyOut, valueOut> extends Thread {

    HashMap<keyOut, valueOut> out;
    private RecordReader recordReader;

    Mapper(InputBlock s) {
        recordReader = new LineRecordReader(s);
        out = new HashMap<>();
    }

    protected abstract void map(keyIn key, valueIn value) throws IOException, InterruptedException;

    @Override
    public void run() {
        run2();
    }

    public void run2() {
        try {
            while (recordReader.hasNext()) {
                map((keyIn) recordReader.getKey(), (valueIn) recordReader.getValue());
            }
            System.out.println("Thread out ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
