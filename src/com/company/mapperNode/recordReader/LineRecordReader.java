package com.company.mapperNode.recordReader;

import com.company.input.InputBlock;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class LineRecordReader implements RecordReader<Integer, String> {
    private InputBlock splitBlockInfo;
    private RandomAccessFile randomAccessFile;
    private Integer lineNumber;
    private Long pos;


    public boolean hasNext() {
        return pos < splitBlockInfo.getLength();
    }

    @Override
    public Integer getKey() {
        return lineNumber++;
    }

    public LineRecordReader(InputBlock b) {
        splitBlockInfo = b;
        lineNumber = 0;
        pos = splitBlockInfo.getFirstByteLocation();
        try {
            randomAccessFile = new RandomAccessFile(b.getDataLocation(), "r");
            randomAccessFile.seek(splitBlockInfo.getFirstByteLocation());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getValue() throws IOException {//debug this function
        String retVal;
        retVal = new String(randomAccessFile.readLine().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        pos += retVal.length() + 1;  //+1 next time start read from next line.
        return retVal;
    }

    @Override
    public void close() throws IOException {
        randomAccessFile.close();
    }
}
