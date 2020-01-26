package com.company.Mapper;

import com.company.any.Writable;

import java.io.*;
import java.net.Socket;
import java.util.Map;

public class INPUT_TO_REDUCERS implements Requester {
    private Socket socket = null;
    private DataInputStream in = null;
    private DataOutputStream socketOut = null;
    private String INPUT_TO_REDUCERS = "INPUT_TO_REDUCERS";

    public INPUT_TO_REDUCERS(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            socketOut = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void request(Socket s) {
        try {
            socketOut.writeUTF(INPUT_TO_REDUCERS);
            for (Map.Entry<Object, Object> entry : Mapper.out.entrySet()) {
                socketOut.writeChars((String) entry.getKey());
                socketOut.writeInt((Integer) entry.getValue());
                System.out.println("Key = " + entry.getKey() +
                        ", Value = " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class StringWritable implements Writable, Comparable<String> {
    private String s = null;

    StringWritable(String s) {
        this.s = s;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(s);
    }

    @Override
    public void read(DataInput in) throws IOException {
        s = in.readUTF();
    }

    @Override
    public int compareTo(String o) {
        return s.compareTo(o);
    }

    @Override
    public int hashCode() {
        return s.hashCode(); //Math.abs(key.getFirst().hashCode() % numPartitions)
    }

    @Override
    public boolean equals(Object anObject) {
        return s.hashCode() == anObject.hashCode();
    }
}

class LongWritable implements Writable {
    private Long l = null;

    LongWritable(Long l) {
        this.l = l;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(l);
    }

    @Override
    public void read(DataInput in) throws IOException {
        l = in.readLong();
    }


    @Override
    public int hashCode() {
        return l.hashCode(); //Math.abs(key.getFirst().hashCode() % numPartitions)
    }

    @Override
    public boolean equals(Object anObject) {
        return l.hashCode() == anObject.hashCode();
    }
}
