package com.company.mapperNode;

import com.company.input.InputBlock;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class TaskExecutor implements Observer {
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Override
    public void update(Observable o, Object arg) {

        executorService.submit(new MapperFunction((InputBlock) arg));
    }

    public Map<Object, Object> getResult() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Mapper.MapperOut;
    }
}
