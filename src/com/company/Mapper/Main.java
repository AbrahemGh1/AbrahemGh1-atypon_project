package com.company.Mapper;

import java.io.IOException;
import java.util.Collections;
import java.util.TreeMap;

interface Partitioner<K2, V2> {
    int getPartition(K2 key, V2 value, int numReduce);
}

public class Main {
    public static TaskExecutor taskExecutor = new TaskExecutor();

    public static void main(String[] args) throws IOException, InterruptedException {

        // TimeUnit.SECONDS.sleep(5);

//        MapperRequester mapperListener = new MapperRequester(2000, "REQUEST_TASK");
//        mapperListener.start();
        // MapperListener mapperListener1 = new MapperListener(2000, "REQUEST_CODE");
        MapperRequester mapperRequester = new MapperRequester(3000, "REDUCERS_IP_ADDRISS");
        mapperRequester.start();
        Mapper.out = Collections.synchronizedMap(new TreeMap<>());
        Mapper.out.put("Abrahem", 1);
        Mapper.out.put("Baker", 1);
        Mapper.out.put("Gh", 1);

        MapperRequester mapperRequester = new MapperRequester(3000, "INPUT_TO_REDUCERS");
        mapperRequester.start();


//        mapperListener.start();
//        mapperListener.join();
//        taskExecutor.getResult();
//        System.out.println(Mapper.out.size());
//        HashCodePartitioner hcp= new HashCodePartitioner();
//        System.out.println(hcp.getPartition("Abrahem",22,2));


    }
}

class HashCodePartitioner implements Partitioner {
    @Override
    public int getPartition(Object key, Object value, int numPartitions) {
        return (key.hashCode() & Integer.MAX_VALUE) % numPartitions;
    }
}


