package com.company.io;

import com.company.split.SplitBlockInfo;

import java.util.ArrayList;
import java.util.Objects;

public class InputSplitHub {
    private static ArrayList<SplitBlockInfo> inputSplits = new ArrayList<>();

    public static void add(SplitBlockInfo e) {
        inputSplits.add(e);
    }

    public synchronized SplitBlockInfo getNewInputSplit() {
        return inputSplits.get(0);
    }

    public void setInputSplits(ArrayList<SplitBlockInfo> e) {
        Objects.requireNonNull(e);
        inputSplits = e;
    }
}
