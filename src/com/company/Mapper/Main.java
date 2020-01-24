package com.company.Mapper;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
//        SplitBlockInfo b = new SplitBlockInfo();
//        b.setFirstByteLocation(0);
//        b.setLength((long) 162);
//        b.setDataLocation("/home/abrahem/IdeaProjects/untitled3/TestSplitFile");
//        Mapper m = new myMapper(b);
//        m.start();

        //       ThreadPoolExecutor TNumber= (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        MapperListener mapperListener = new MapperListener(2000, "REQUEST_TASK");
        mapperListener.run();
//        for (SplitBlockInfo s : mapperListener.ml) {
//            Mapper<? extends Integer, ? extends String, ? extends String, ? extends Integer> m = new myMapper(s);
//            m.start();
//        }

        System.out.println("Finish");

    }
}
//91636