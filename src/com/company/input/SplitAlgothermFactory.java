package com.company.input;

import java.io.FileNotFoundException;
import java.nio.file.Path;

public class SplitAlgothermFactory {
    public SplitAlgotherm createSplitAlgotherm(Path filePath) throws FileNotFoundException {
        return new DefaultTextSplitAlgotherm(filePath);
    }
}


