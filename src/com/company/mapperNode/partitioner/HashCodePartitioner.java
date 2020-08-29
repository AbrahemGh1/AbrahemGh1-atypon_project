package com.company.mapperNode.partitioner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashCodePartitioner implements Partitioner<String> {
    private int numberOfReducers;
    private final Map<Object, Object> keyValue;
    private Map<Integer, List<Map.Entry>> outGroupByReducer = new HashMap<>();

    public HashCodePartitioner(Map keyValue, int numberOfReducers) {
        this.numberOfReducers = numberOfReducers;
        this.keyValue = keyValue;
    }

    @Override
    public int getPartition(String key, int numberOfReducers) {
        return (key.hashCode() & Integer.MAX_VALUE) % numberOfReducers;
    }

    public Map<Integer, List<Map.Entry>> getOutputAsGroup() {
        for (Map.Entry<Object, Object> entry : keyValue.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());//remove this later
            String key = (String) entry.getKey();
            int reducerNodeNumber = this.getPartition(key, numberOfReducers);
            if (outGroupByReducer.containsKey(reducerNodeNumber)) {
                outGroupByReducer.get(reducerNodeNumber).add(entry);
            } else {
                List<Map.Entry> keysValues = new ArrayList<>();
                keysValues.add(entry);
                outGroupByReducer.put(reducerNodeNumber, keysValues);
            }
        }
        return outGroupByReducer;
    }
}
