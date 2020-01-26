package com.company.Mapper;

import com.company.input.InputBlock;

import java.io.IOException;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static taskExecutor taskExecutor = new taskExecutor();

    public static void main(String[] args) throws IOException, InterruptedException {

        // TimeUnit.SECONDS.sleep(5);

        MapperListener mapperListener = new MapperListener(2000, "REQUEST_TASK");
        mapperListener.start();
        mapperListener.join();
        taskExecutor.getResult();
        System.out.println(Mapper.out.size());


        //18919

    }
}
//91636

class taskExecutor<keyOut, valueOut> implements Observer {
    ExecutorService executorService = Executors.newFixedThreadPool(4);

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
        return Mapper.out;
    }
}


