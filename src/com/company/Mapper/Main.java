package com.company.Mapper;

import com.company.input.SplitBlockInfo;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        SplitBlockInfo b = new SplitBlockInfo();
        b.setFirstByteLocation(0);
        b.setLength((long) 162);
        b.setDataLocation("/home/abrahem/IdeaProjects/untitled3/TestSplitFile");
        Mapper m = new myMapper(b);
        // m.start();
        m.run2();
//        MapperListener mapperListener = new MapperListener(2000);
//        mapperListener.start();
//        mapperListener.join();
//        Mapper m = new myMapper(MapperListener.ml.get(0));


        System.out.println("Finish");

    }
}
