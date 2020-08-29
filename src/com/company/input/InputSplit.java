package com.company.input;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface InputSplit {
    /**
     * Returns InputBlock as a List.
     *
     * @throws IOException if this file is closed
     */
    List<InputBlock> getSplits() throws IOException;
}

