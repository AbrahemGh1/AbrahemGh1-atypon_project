package com.company.mapperNode;

import com.company.sw.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

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
