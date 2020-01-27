package com.company.input;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSplit implements InputSplit {
    //Consider to use Thread her but note that the input split should be sorted before go to mapper for performance issue.
    private SplitAlgotherm sa;

    public FileSplit(Path FilePath, SplitAlgothermFactory s) throws FileNotFoundException {
        sa = s.getSplitAlgotherm(FilePath);
    }

    @Override
    public List<InputBlock> getSplits() throws FileNotFoundException {
        List<InputBlock> ls = new ArrayList<>();
        while (isSplittable()) {
            ls.add(getSplit());
        }
        Collections.sort(ls, Collections.reverseOrder());
        return ls;
    }

    public InputBlock getSplit() throws FileNotFoundException {
        if (isSplittable())
            return sa.MakeSplit();
        return null;
    }

    public boolean isSplittable() {
        return sa.isSplittable();
    }
}
