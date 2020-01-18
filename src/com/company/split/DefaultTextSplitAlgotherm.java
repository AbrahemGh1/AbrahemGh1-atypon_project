package com.company.split;

import java.io.*;
import java.nio.file.Path;

class DefaultTextSplitAlgotherm implements SplitAlgotherm {
    private File file;
    private RandomAccessFile dataInputStream;
    private long startSplitAtPosition;
    private long maxBlockSize;


    DefaultTextSplitAlgotherm(Path filePath) throws FileNotFoundException {
        file = new File(String.valueOf(filePath));
        dataInputStream = new RandomAccessFile(file.getAbsolutePath(), "r");
        startSplitAtPosition = 0;
        maxBlockSize = 64;
    }


    @Override
    public SplitBlockInfo MakeSplit() throws FileNotFoundException {// add ur algo here
        SplitBlockInfo sp = new SplitBlockInfo();
        sp.setFirstByteLocation(startSplitAtPosition);
        getLengthOfSplit();
        sp.setLength(this.getLengthOfSplit());
        return sp;
    }

    private boolean isSplittableAtPosition() {
        return true;
    }

    long getLengthOfSplit() {
        long lastIndex = startSplitAtPosition + maxBlockSize;
        if (startSplitAtPosition + maxBlockSize > file.length())
            return file.length();

        return getNextEmptyCharactertFrom(lastIndex);
    }

    private long getNextEmptyCharactertFrom(long startOffset) {
        try {
            dataInputStream.seek(startOffset);
            byte[] bytes = new byte[10];//10 is the max allowed size to extend.
            String str = new String(bytes);
            dataInputStream.readFully(bytes);
            return str.indexOf(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
