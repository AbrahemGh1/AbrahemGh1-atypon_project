package com.company.input;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileSplit implements InputSplit {
    //Consider to use Thread her but note that the input split should be sorted before go to mapper for performance issue.
    private SplitAlgotherm sa;

    public FileSplit(Path FilePath, SplitAlgothermFactory s) throws FileNotFoundException {
        sa = s.createSplitAlgotherm(FilePath);
    }

    @Override
    public List<InputBlock> getSplits() throws IOException {
        List<InputBlock> ls = new ArrayList<>();
        while (isSplittable()) {
            ls.add(getSplit());
        }
        ls.sort(Collections.reverseOrder());
        return ls;
    }

    public InputBlock getSplit() throws IOException {
        if (isSplittable())
            return sa.MakeSplit();
        return null;
    }

    public boolean isSplittable() {
        return sa.isSplittable();
    }
}
