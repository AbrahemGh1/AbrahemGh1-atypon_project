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
        randomAccessFile = new RandomAccessFile(String.valueOf(filePath), "r");
        startSplitAtPosition = 0;
        maxBlockSize = 150;
    }

    @Override
    public long getLength() {
        return file.length();
    }

    @Override
    public synchronized boolean isSplittable() {
        return startSplitAtPosition < getLength();
    }

    @Override
    public synchronized InputBlock MakeSplit() {// add ur algo here
        InputBlock sp = new InputBlock();
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

        return getNextEmptyCharacterText2(lastIndex);
    }

    private long getNextEmptyCharacterText2(long startOffset) {
        //1-this need to refactor later, dot use String, use StringBuff
        //2 do't read byte by byte it's too slow.
        int nextEmptyChar = 0;
        byte[] bytes = new byte[1];
        try {
            randomAccessFile.seek(startOffset);
            do {
                nextEmptyChar++;
                randomAccessFile.read(bytes);
                String t = new String(bytes);
                if (t.equalsIgnoreCase(" "))
                    break;
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nextEmptyChar + startOffset;
    }

}
