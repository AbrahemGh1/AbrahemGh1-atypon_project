package com.company.communications;

import com.company.input.SplitBlockInfo;

import java.io.Closeable;
import java.io.IOException;
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
        if (!hasTask())
            throw new IllegalStateException("can't get from empty TaskPool");//this need custom exception.
        SplitBlockInfo temp = null;
        temp = inputSplits.get(0);
        inputSplits.remove(0);
        return temp;
    }

    public synchronized List getNewInputSplit(int number) {
        ArrayList<SplitBlockInfo> inputSplits = new ArrayList<SplitBlockInfo>();
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

    public boolean hasTask() {
        return !inputSplits.isEmpty();
    }

    @Override
    public void close() throws IOException {
        isClosed = true;
    }

    public boolean isClosed() {
        return isClosed;
    }
}
