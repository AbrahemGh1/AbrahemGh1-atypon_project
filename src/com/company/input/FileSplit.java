package com.company.input;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSplit implements InputSplit {
    private long start;
    private long length;
    private SplitAlgotherm sa;

    public FileSplit(Path FilePath) throws FileNotFoundException {
        start = 0;
        sa = new DefaultTextSplitAlgotherm(FilePath);
        length = sa.getLength();
    }

    @Override
    public List<SplitBlockInfo> getSplits() throws FileNotFoundException {
        List<SplitBlockInfo> ls = new ArrayList<>();
        while (isSplittable()) {
            SplitBlockInfo s = getSplitNow();
            ls.add(s);
        }
        Collections.sort(ls);
        return ls;
    }

    public SplitBlockInfo getSplitNow() throws FileNotFoundException {
        if (isSplittable())
            return sa.MakeSplit();
        return null;
    }

    private boolean isSplittable() {
        return sa.isSplittable();
    }
}
