package com.company.input;

import java.io.FileNotFoundException;

interface SplitAlgotherm {
    SplitBlockInfo MakeSplit() throws FileNotFoundException;

    long getLength();

    boolean isSplittable();
}
