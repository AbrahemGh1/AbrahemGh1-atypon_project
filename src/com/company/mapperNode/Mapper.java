package com.company.mapperNode;

import com.company.input.InputBlock;
import com.company.mapperNode.recordReader.LineRecordReader;
import com.company.mapperNode.recordReader.RecordReader;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Mapper<keyIn, valueIn, keyOut, valueOut> implements Runnable {

    private RecordReader recordReader;
    static AtomicInteger numberOfThreadsPerMapper = new AtomicInteger(2);
    static final Map<Object, Object> MapperOut = Collections.synchronizedMap(new TreeMap<>());


    Mapper(InputBlock s) {
        recordReader = new LineRecordReader(s);
    }

    protected abstract  void map(keyIn key, valueIn value) throws IOException, InterruptedException;

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
            // e.printStackTrace();
        }
    }
}
