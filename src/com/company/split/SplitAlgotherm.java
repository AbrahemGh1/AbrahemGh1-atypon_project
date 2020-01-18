package com.company.split;

import java.io.FileNotFoundException;
import java.nio.file.Path;

interface SplitAlgotherm {
    SplitBlockInfo MakeSplit() throws FileNotFoundException;
}
