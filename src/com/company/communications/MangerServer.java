package com.company.communications;

import com.company.input.FileSplit;
import com.company.input.SplitBlockInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MangerServer {
    public static void main(String[] args) throws FileNotFoundException {
        FileSplit fs = new FileSplit(Paths.get("/home/abrahem/Desktop/input.txt"));
        List<SplitBlockInfo> s = fs.getSplits();
//8364
        try {
            TaskPool taskPool = new TaskPool();
            taskPool.setInputSplits((ArrayList<SplitBlockInfo>) s);
            uploadBlockRequestHandler.setE(taskPool);
            ManagerListener ml = new ManagerListener(2000);
            ml.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (; ; ) ;
    }

}
