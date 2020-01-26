package com.company.managerNode;

import com.company.input.FileSplit;
import com.company.input.InputBlock;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileSplit fs = new FileSplit(Paths.get("/home/abrahem/Desktop/input.txt"));
        List<InputBlock> s = fs.getSplits();


//        s.addAll(s);
//        s.addAll(s);
//        s.addAll(s);
//        s.addAll(s);
//        s.addAll(s);


//        List<InputBlock> s2 = new ArrayList<InputBlock>();
//        s2.add(s.get(0));
//        s = s2;
//8364

        TaskPool taskPool = new TaskPool();
        taskPool.setInputSplits((ArrayList<InputBlock>) s);
        uploadTaskHandler.setE(taskPool);
        ManagerListener ml = new ManagerListener(2000);
        ml.start();
    }
}


