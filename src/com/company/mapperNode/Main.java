package com.company.mapperNode;

import java.io.IOException;
import java.util.*;

interface Partitioner<K2, V2> {
    int getPartition(K2 key, V2 value, int numberOfReducers);
}

public class Main {

    public static TaskExecutor taskExecutor = new TaskExecutor();

    public static void main(String[] args) throws IOException, InterruptedException {
        int mangerPort = 2000;

        // TimeUnit.SECONDS.sleep(5);

        // MapperListener mapperListener1 = new MapperListener(2000, "REQUEST_CODE");
        MapperRequester mapperRequester = new MapperRequester(mangerPort, "REQUEST_REDUCERS_PORT");
        mapperRequester.start();
        mapperRequester.join();
        MapperRequester mapperListener = new MapperRequester(2000, "REQUEST_TASK");
        mapperListener.start();
        mapperListener.join();
        int numberOfReducers = REDUCERS_PORT_REQUEST.REDUCERS_NODE.size();
        HashCodePartitioner Partitioner = new HashCodePartitioner();
        Map<Object, Object> SortedMapperOut = new TreeMap<>(Mapper.MapperOut);
        Map<Integer, List<Map.Entry>> outGroupByReducer = new HashMap<>();

        for (Map.Entry<Object, Object> entry : SortedMapperOut.entrySet()) {
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());

            String key = (String) entry.getKey();
            Integer value = (Integer) entry.getValue();


            int reducerNodeNumber = Partitioner.getPartition(key, value, numberOfReducers);//review, vlue maybe deiff with same value in deff mapper?!!!
            if (outGroupByReducer.containsKey(reducerNodeNumber)) {
                outGroupByReducer.get(reducerNodeNumber).add(entry);
            } else {
                List<Map.Entry> keysValues = new ArrayList<>();
                keysValues.add(entry);
                outGroupByReducer.put(reducerNodeNumber, keysValues);
            }
        }
        // ((Map.Entry)(outGroupByReducer.get(1).get(0))).getKey();
        System.out.println("a");

        Mapper.MapperOut = Collections.synchronizedMap(new TreeMap<>());
        Mapper.MapperOut.put("Abrahem", 1);
        Mapper.MapperOut.put("Baker", 1);
        Mapper.MapperOut.put("Gh", 1);

//        MapperRequester mapperRequester = new MapperRequester(3000, "INPUT_TO_REDUCERS");
//        mapperRequester.start();


//        mapperListener.start();
//        mapperListener.join();
//        taskExecutor.getResult();
//        System.out.println(Mapper.out.size());
//        HashCodePartitioner hcp= new HashCodePartitioner();
//        System.out.println(hcp.getPartition("Abrahem",22,2));


    }

}

class HashCodePartitioner implements Partitioner<String, Integer> {

    @Override
    public int getPartition(String key, Integer value, int numberOfReducers) {
        return (key.hashCode() & Integer.MAX_VALUE) % numberOfReducers;
    }
}


