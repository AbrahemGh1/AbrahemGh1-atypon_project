package com.company.input;

import com.company.any.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Objects;

public class SplitBlockInfo implements Comparable, Writable {
    private long firstByteLocation;
    private Long length;
    private String dataLocation;


    public String getDataLocation() {
        return dataLocation;
    }

    public void setDataLocation(String dataLocation) {
        this.dataLocation = dataLocation;
    }


    public void setLength(Long length) {
        this.length = length;
    }

    public long getFirstByteLocation() {
        return firstByteLocation;
    }

    public void setFirstByteLocation(long firstByteLocation) {
        this.firstByteLocation = firstByteLocation;
    }


    public Long getLength() {
        return length;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        Objects.requireNonNull(out, "out object can't be null");
        out.writeLong(firstByteLocation);
        out.writeLong(length);
        out.writeUTF(dataLocation);
    }

    @Override
    public void read(DataInput in) throws IOException {
        Objects.requireNonNull(in, "in Object can't be null");
        firstByteLocation = in.readLong();
        length = in.readLong();
        dataLocation = in.readUTF();
    }

    @Override
    public int compareTo(Object o) {
        assert o instanceof SplitBlockInfo;
        return this.getLength().compareTo(((SplitBlockInfo) o).getLength());
    }
}
