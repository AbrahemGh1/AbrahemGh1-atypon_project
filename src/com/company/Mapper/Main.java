package com.company.Mapper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        // TimeUnit.SECONDS.sleep(5);

        MapperListener mapperListener = new MapperListener(2000, "REQUEST_TASK");
        mapperListener.run();


    }

    public synchronized void getBusyFlag() throws InterruptedException {
        wait(100);
    }
}
//91636