package com.company.split;

import com.company.any.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

public class SplitBlockInfo implements Comparable, Serializable, Writable {
    private long firstByteLocation;
    private Long length;


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
    public int compareTo(Object o) {
        assert o instanceof SplitBlockInfo;
        return this.getLength().compareTo(((SplitBlockInfo) o).getLength());
    }

    @Override
    public void write(DataOutput out) throws IOException {
        Objects.requireNonNull(out, "out can't be null");
        out.writeLong(firstByteLocation);
        out.writeLong(length);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        Objects.requireNonNull(in, "in can't be null");
        firstByteLocation = in.readLong();
        length = in.readLong();

    }
}
