package com.company.mapperNode.partitioner;

public interface Partitioner<outKey> {
    int getPartition(outKey key, int numberOfReducers);
}
