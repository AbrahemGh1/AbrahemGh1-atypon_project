package com.company.managerNode;

import com.company.input.FileSplit;
import com.company.input.InputBlock;
import com.company.input.SplitAlgothermFactory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ManagerMain {
    public static void main(String[] args) throws IOException {
        FileSplit fs = new FileSplit(Paths.get("/home/abrahem/IdeaProjects/untitled3/TestSplitFile"), new SplitAlgothermFactory());
        List<InputBlock> s = fs.getSplits();

        TaskPool taskPool = new TaskPool();
        taskPool.setInputSplits((ArrayList<InputBlock>) s);
        uploadTaskHandler.setTasks(taskPool);

        ManagerListener ml = new ManagerListener(2000);
        ml.start();
    }
}


