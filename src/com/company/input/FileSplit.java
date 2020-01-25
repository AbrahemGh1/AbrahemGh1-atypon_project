package com.company.input;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSplit implements InputSplit {
    //Consider to use Thread her but note the input split should be sorted before go to mapper for performance issue
    private long start;
    private long length;
    private SplitAlgotherm sa;

    public FileSplit(Path FilePath) throws FileNotFoundException {
        start = 0;
        sa = new DefaultTextSplitAlgotherm(FilePath);
        length = sa.getLength();
    }

    @Override
    public List<InputBlock> getSplits() throws FileNotFoundException {
        List<InputBlock> ls = new ArrayList<>();
        while (isSplittable()) {
            ls.add(getSplitNow());
        }
        Collections.sort(ls, Collections.reverseOrder());
        return ls;
    }

    public InputBlock getSplitNow() throws FileNotFoundException {
        if (isSplittable())
            return sa.MakeSplit();
        return null;
    }

    public boolean isSplittable() {
        return sa.isSplittable();
    }
}
