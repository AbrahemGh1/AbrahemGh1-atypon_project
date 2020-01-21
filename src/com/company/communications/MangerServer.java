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
        // List<? extends SplitBlockInfo> s = fs.getSplits();
        List<SplitBlockInfo> s = fs.getSplits();
        try {
            InputSplitsHub hub = new InputSplitsHub();
            hub.setInputSplits((ArrayList<SplitBlockInfo>) s);
            uploadBlockRequestHandler.setE(hub);
            ManagerListener ml = new ManagerListener(2000);
            ml.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (; ; ) ;
    }
}
