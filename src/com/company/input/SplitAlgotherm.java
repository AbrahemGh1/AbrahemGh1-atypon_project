package com.company.input;

import java.io.FileNotFoundException;

interface SplitAlgotherm {
    InputBlock MakeSplit() throws FileNotFoundException;

    long getLength();

    boolean isSplittable();
}
