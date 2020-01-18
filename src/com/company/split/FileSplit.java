package com.company.split;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class FileSplit implements InputSplit {
    private Path FilePath;
    private long start;
    private long length;
    private SplitAlgotherm sa;

    FileSplit() {
        sa = new DefaultTextSplitAlgotherm(FilePath);
    }

    @Override
    public List<SplitBlockInfo> getSplits() throws FileNotFoundException {
        List<SplitBlockInfo> ls = new ArrayList<>();

        while (start < length) {
            if(isSplittable()){
            SplitBlockInfo s = sa.MakeSplit();
            start += s.getLength();
            ls.add(s);
            }
        }
        Collections.sort(ls);
        return ls;
    }
    private boolean isSplittable (){



        return true;
    }
}
