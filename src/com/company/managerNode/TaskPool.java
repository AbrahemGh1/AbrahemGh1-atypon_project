package com.company.managerNode;

import com.company.input.SplitBlockInfo;

import java.io.Closeable;
import java.util.*;

public class TaskPool implements Closeable {
    Queue<Integer> s = new PriorityQueue<>();//this should be used instead of Array
    private ArrayList<SplitBlockInfo> inputSplits = new ArrayList<>();
    private boolean isClosed;

    public TaskPool() {
        isClosed = false;
    }

    public void add(SplitBlockInfo e) {
        inputSplits.add(e);
    }

    public synchronized SplitBlockInfo getNewInputSplit() {
        if (!tasksFinished())
            throw new IllegalStateException("can't get from empty TaskPool");//this need custom exception.
        SplitBlockInfo temp;
        temp = inputSplits.get(0);
        inputSplits.remove(0);
        isClosed = !tasksFinished();
        return temp;
    }

    public synchronized List<? extends SplitBlockInfo> getNewInputSplit(int number) {
        ArrayList<SplitBlockInfo> inputSplits = new ArrayList<>();
        int counter = 0;
        while (counter < number) {
            inputSplits.add(getNewInputSplit());
            counter++;
        }
        return inputSplits;
    }

    public void setInputSplits(ArrayList<SplitBlockInfo> e) {
        Objects.requireNonNull(e);
        inputSplits = e;
    }

    public boolean tasksFinished() {
        return !inputSplits.isEmpty();
    }

    @Override
    public void close() {
        isClosed = true;
    }

    public boolean isClosed() {
        return isClosed;
    }
}
