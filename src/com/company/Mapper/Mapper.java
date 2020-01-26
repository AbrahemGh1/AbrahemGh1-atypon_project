package com.company.Mapper;

import com.company.input.InputBlock;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Mapper<keyIn, valueIn, keyOut, valueOut> extends Thread {

    static Map<Object, Object> out = null;
    private RecordReader recordReader;
    static AtomicInteger numberOfThreads = new AtomicInteger(0);

    Mapper(InputBlock s) {
        recordReader = new LineRecordReader(s);
    }

    static {
        out = Collections.synchronizedMap(new TreeMap<>());
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
            //   System.out.println("Thread out." + numberOfThreads.getAndIncrement());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
