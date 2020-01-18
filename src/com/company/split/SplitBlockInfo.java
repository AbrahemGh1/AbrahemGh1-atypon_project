package com.company.split;

class SplitBlockInfo implements Comparable {
    private long firstByteLocation;

    public void setLength(Long length) {
        this.length = length;
    }

    private Long length;

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
}
