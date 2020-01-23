package com.company.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;


class DefaultTextSplitAlgotherm implements SplitAlgotherm {
    private File file;
    private RandomAccessFile randomAccessFile;
    private long startSplitAtPosition;
    private long maxBlockSize;


    public DefaultTextSplitAlgotherm(Path filePath) throws FileNotFoundException {
        file = new File(String.valueOf(filePath));
        randomAccessFile = new RandomAccessFile(file.getAbsolutePath(), "r");
        startSplitAtPosition = 0;
        maxBlockSize = 150;
    }

    @Override
    public long getLength() {
        return file.length();
    }

    @Override
    public synchronized boolean isSplittable() {
        return startSplitAtPosition < file.length();
    }

    @Override
    public synchronized SplitBlockInfo MakeSplit() throws FileNotFoundException {// add ur algo here
        SplitBlockInfo sp = new SplitBlockInfo();
        sp.setFirstByteLocation(startSplitAtPosition);
        this.startSplitAtPosition = this.getLengthOfSplit();
        sp.setLength(startSplitAtPosition);
        sp.setDataLocation(file.getAbsolutePath());
        return sp;
    }

    private boolean isSplittableAtPosition(long pos) throws IOException {
        if (pos == file.length()) return false;
        return randomAccessFile.readUTF().equalsIgnoreCase(" ");
    }

    long getLengthOfSplit() {
        long lastIndex = startSplitAtPosition + maxBlockSize;
        if (lastIndex >= file.length())
            return file.length();//need test

        return getNextEmptyCharacterFromFile(lastIndex);
    }

    private long getNextEmptyCharacterFromFile(long startOffset) {
        try {
            randomAccessFile.seek(startOffset);
            byte[] bytes = new byte[10];//10 the max size allowed to extend split.
            randomAccessFile.readFully(bytes);
            String str = new String(bytes);
            int temp = str.indexOf(" ");
            return str.indexOf(" ") + startOffset;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
