package com.company.mapperNode;

import com.company.sw.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

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
