package com.company.mapperNode;

import com.company.mapperNode.partitioner.HashCodePartitioner;
import com.company.mapperNode.partitioner.Partitioner;
import com.company.mapperNode.requester.REDUCERS_PORT_REQUEST;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static TaskExecutor taskExecutor = new TaskExecutor();

    public static void main(String[] args) throws IOException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        int mangerPort = 2000;

        // TimeUnit.SECONDS.sleep(5);

        // MapperListener mapperListener1 = new MapperListener(2000, "REQUEST_CODE");
        MapperRequester task_request = new MapperRequester(2000, "REQUEST_TASK");
        task_request.start();

        MapperRequester reducers_port_request = new MapperRequester(2000, "REQUEST_REDUCERS_PORT");
        reducers_port_request.start();
        task_request.join();
        reducers_port_request.join();

        //Partition Phase
        HashMap<Integer, Integer> reducersNodes = REDUCERS_PORT_REQUEST.getReducerNodeAsHashMap();
        int numberOfReducers = reducersNodes.size();
        Map<Object, Object> intermedMappariateOutput = taskExecutor.getResult();
        Partitioner p = new HashCodePartitioner(intermedMappariateOutput, numberOfReducers);
        Map<Integer, List<Map.Entry>> outGroupByReducer = ((HashCodePartitioner) p).getOutputAsGroup();


        //SHUFFLE_Phase
        SHUFFLE_REQUEST.setMapperNodeOutput(outGroupByReducer);
        MapperRequester shuffle_request;
        for (int i = 0; i < numberOfReducers; i++) {
            int reducerPortNumber = reducersNodes.get(i);
            System.out.println(reducerPortNumber);

            shuffle_request = new MapperRequester(reducerPortNumber, "SHUFFLE_REQUEST");
            executorService.submit(shuffle_request);
        }
        executorService.shutdown();
        executorService.awaitTermination(3000, TimeUnit.SECONDS);


//        MapperRequester shuffle_request = new MapperRequester(3000, "SHUFFLE_REQUEST");
//        shuffle_request.start();


//        mapperListener.start();
//        mapperListener.join();
//        taskExecutor.getResult();
//        System.out.println(Mapper.out.size());
//        HashCodePartitioner hcp= new HashCodePartitioner();
//        System.out.println(hcp.getPartition("Abrahem",22,2));


    }

}


