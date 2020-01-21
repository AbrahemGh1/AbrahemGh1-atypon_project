package com.company.communications;

import com.company.input.SplitBlockInfo;

import java.util.ArrayList;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class InputSplitsHub {
    Queue<Integer> s = new PriorityQueue<>();//this should be used insted of Array
    private ArrayList<SplitBlockInfo> inputSplits = new ArrayList<>();

    public void add(SplitBlockInfo e) {
        inputSplits.add(e);
    }

    public synchronized SplitBlockInfo getNewInputSplit() {


        SplitBlockInfo temp = inputSplits.get(0);
        inputSplits.remove(0);
        return temp;
    }

    public void setInputSplits(ArrayList<SplitBlockInfo> e) {
        Objects.requireNonNull(e);
        inputSplits = e;
    }

    public boolean h() {
        return inputSplits.isEmpty();
    }
}
