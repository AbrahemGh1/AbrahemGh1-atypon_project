package com.company.input;

import com.company.Mapper.Mapper;
import com.company.Mapper.myMapper;
import com.company.communications.InputSplitsHub;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileSplit fs = new FileSplit(Paths.get("/home/abrahem/Desktop/input.txt"));
        // List<? extends SplitBlockInfo> s = fs.getSplits();
        List<? extends SplitBlockInfo> s = fs.getSplits();
        InputSplitsHub.setInputSplits((ArrayList<SplitBlockInfo>) s);
        int counter = 0;
        for (SplitBlockInfo t :
                s) {
            Mapper m = new myMapper(t);
            m.start();
            System.out.println(counter++);
        }
        System.out.println("yes");
    }

}
