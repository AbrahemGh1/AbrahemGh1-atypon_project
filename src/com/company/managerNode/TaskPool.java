package com.company.managerNode;

import com.company.input.InputBlock;

import java.io.Closeable;
import java.util.*;

public class TaskPool implements Closeable {
    Queue<Integer> s = new PriorityQueue<>();//this should be used instead of Array
    private ArrayList<InputBlock> inputSplits = new ArrayList<>();
    private boolean isClosed;

    public TaskPool() {
        isClosed = false;
    }

    public void add(InputBlock e) {
        inputSplits.add(e);
    }

    public synchronized InputBlock getNewInputSplit() {
        if (isEmpty())
            throw new IllegalStateException("can't get from empty TaskPool");//this need custom exception.
        InputBlock temp;
        temp = inputSplits.get(0);
        inputSplits.remove(0);
        isClosed = isEmpty();
        return temp;
    }

    public synchronized List<? extends InputBlock> getNewInputSplit(int number) {
        ArrayList<InputBlock> inputSplits = new ArrayList<>();
        int counter = 0;
        while (counter < number) {
            inputSplits.add(getNewInputSplit());
            counter++;
        }
        return inputSplits;
    }

    public void setInputSplits(ArrayList<InputBlock> e) {
        Objects.requireNonNull(e);
        inputSplits = e;
    }

    public synchronized boolean isEmpty() {
        return inputSplits.isEmpty();
    }

    @Override
    public void close() {
        isClosed = true;
    }

    public boolean isClosed() {
        return isClosed;
    }
}
