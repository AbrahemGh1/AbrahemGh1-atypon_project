package com.company.Mapper;

import com.company.input.InputBlock;

import java.io.IOException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static taskExecutor taskExecutor = new taskExecutor();

    public static void main(String[] args) throws IOException, InterruptedException {

        // TimeUnit.SECONDS.sleep(5);

        MapperListener mapperListener = new MapperListener(2000, "REQUEST_TASK");
        mapperListener.start();
        mapperListener.join();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++" + MapperFunction.all);
        List mapperTask = REQUEST_TASK.TasksList;


    }
}
//91636

class taskExecutor<keyOut, valueOut> implements Observer {
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Override
    public void update(Observable o, Object arg) {
        executorService.submit(new MapperFunction((InputBlock) arg));//
    }
}


