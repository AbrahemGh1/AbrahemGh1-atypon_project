package com.company.Mapper;

import com.company.input.SplitBlockInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public abstract class Mapper<keyIn, valueIn, keyOut, valueOut> extends Thread {

    HashMap<keyOut, valueOut> out;
    private RecordReader recordReader;

    Mapper(SplitBlockInfo s) throws FileNotFoundException {

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
        } catch (Exception e) {
            System.out.println("Thread out.");
            e.printStackTrace();
        }
    }

}
