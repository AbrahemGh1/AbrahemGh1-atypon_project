package com.company.test;


import java.util.*;

public class test2 {
    public static void main(String[] args) {
        HashMap<String, Integer> MapperOut = new HashMap<>();
        MapperOut.put("Abrahem", 5);
        MapperOut.put("Ahamad", 3);
        MapperOut.put("test", 1);
        MapperOut.put("Never", null);
        MapperOut.put("A", 194);


        Map<Object, Object> MapperOutFinal = new TreeMap<>(MapperOut);
        Map<Integer, List<Map.Entry>> outGroupByReducer = new HashMap<>();
        HashCodePartitioner2 Partitioner = new HashCodePartitioner2();

        for (Map.Entry<Object, Object> entry : MapperOutFinal.entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
            String key = (String) entry.getKey();
            Integer value = (Integer) entry.getValue();

            int reducerNodeNumber = Partitioner.getPartition(key, value, 1);
            if (outGroupByReducer.containsKey(reducerNodeNumber)) {
                outGroupByReducer.get(reducerNodeNumber).add(entry);
            } else {
                List<Map.Entry> keysValues = new ArrayList<>();
                keysValues.add(entry);
                outGroupByReducer.put(reducerNodeNumber, keysValues);
            }
        }
    }
}

class HashCodePartitioner2 {
    public int getPartition(String key, Integer value, int numberOfReducers) {
        return (key.hashCode() & Integer.MAX_VALUE) % numberOfReducers;
    }
}