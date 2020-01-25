package com.company.managerNode;

import com.company.input.FileSplit;
import com.company.input.SplitBlockInfo;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileSplit fs = new FileSplit(Paths.get("/home/abrahem/Desktop/input.txt"));
        List<SplitBlockInfo> s = fs.getSplits();


//        List<SplitBlockInfo> s2= new ArrayList<SplitBlockInfo>();
//        s2.add(s.get(0));
//        s=s2;
//8364
        TaskPool taskPool = new TaskPool();
        taskPool.setInputSplits((ArrayList<SplitBlockInfo>) s);
        uploadTaskHandler.setE(taskPool);
        ManagerListener ml = new ManagerListener(2000);
        ml.start();
    }
}


