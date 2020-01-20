package com.company.input;

import java.io.FileNotFoundException;
import java.util.List;

public interface InputSplit {
    List<SplitBlockInfo> getSplits() throws FileNotFoundException;
}

