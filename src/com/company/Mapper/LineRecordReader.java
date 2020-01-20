package com.company.Mapper;

import com.company.input.SplitBlockInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

class LineRecordReader implements RecordReader<Integer, String> {
    SplitBlockInfo splitBlockInfo;
    RandomAccessFile randomAccessFile;
    private Integer lineNumber;

    LineRecordReader(SplitBlockInfo b) throws FileNotFoundException {
        splitBlockInfo = b;
        lineNumber = 0;
        randomAccessFile = new RandomAccessFile(b.getDataLocation(), "r");
    }

    public boolean hasNext() throws IOException {
        return true;
    }

    @Override
    public Integer getKey() {
        lineNumber++;
        return lineNumber - 1;
    }

    @Override
    public String getValue() throws IOException {
        return randomAccessFile.readLine();
    }

    @Override
    public void close() throws IOException {
        randomAccessFile.close();
    }
}
