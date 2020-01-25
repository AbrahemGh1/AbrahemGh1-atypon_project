package com.company.Mapper;

import com.company.input.InputBlock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

class LineRecordReader implements RecordReader<Integer, String> {
    InputBlock splitBlockInfo;
    RandomAccessFile randomAccessFile;
    private Integer lineNumber;
    private Long pos;

    LineRecordReader(InputBlock b) {
        splitBlockInfo = b;
        lineNumber = 0;
        try {
            randomAccessFile = new RandomAccessFile(b.getDataLocation(), "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
        String retVal = new String(randomAccessFile.readLine().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        System.out.println("[" + retVal + "]");
        pos += retVal.length() + 1;  //+1 next time start read from next line.
        return retVal;
    }

    @Override
    public void close() throws IOException {
        randomAccessFile.close();
    }
}
