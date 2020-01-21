package com.company.Mapper;

import com.company.input.SplitBlockInfo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

class LineRecordReader implements RecordReader<Integer, String> {
    SplitBlockInfo splitBlockInfo;
    RandomAccessFile randomAccessFile;
    private Integer lineNumber;
    private Long pos;

    LineRecordReader(SplitBlockInfo b) throws FileNotFoundException {
        splitBlockInfo = b;
        lineNumber = 0;
        randomAccessFile = new RandomAccessFile(b.getDataLocation(), "r");
        pos = splitBlockInfo.getFirstByteLocation();
    }

    public boolean hasNext() throws IOException {
        return pos < splitBlockInfo.getLength();
    }

    @Override
    public Integer getKey() {
        return lineNumber++;
    }

    @Override
    public String getValue() throws IOException {
        randomAccessFile.seek(pos);
        String retVal = randomAccessFile.readLine();
        pos += retVal.length() + 1;  //+1 start read from next line.
        return retVal;
    }

    @Override
    public void close() throws IOException {
        randomAccessFile.close();
    }
}
