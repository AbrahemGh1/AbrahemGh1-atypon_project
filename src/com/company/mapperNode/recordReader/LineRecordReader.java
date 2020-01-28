package com.company.mapperNode.recordReader;

import com.company.input.InputBlock;
import com.company.mapperNode.recordReader.RecordReader;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class LineRecordReader implements RecordReader<Integer, String> {
    InputBlock splitBlockInfo;
    RandomAccessFile randomAccessFile;
    private Integer lineNumber;
    private Long pos;

    long privPos = 0;

    public boolean hasNext() throws IOException {
        return pos < splitBlockInfo.getLength();
    }

    @Override
    public Integer getKey() {
        return lineNumber++;
    }

    public LineRecordReader(InputBlock b) {
        splitBlockInfo = b;
        lineNumber = 0;
        try {
            randomAccessFile = new RandomAccessFile(b.getDataLocation(), "r");
            randomAccessFile.seek(splitBlockInfo.getFirstByteLocation());
        } catch (IOException e) {
            e.printStackTrace();
        }
        pos = splitBlockInfo.getFirstByteLocation();
    }

    @Override
    public String getValue() throws IOException {
        String retVal = "";
        try {
            retVal = new String(randomAccessFile.readLine().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        } catch (NullPointerException e) {
            //  System.out.println("[" + retVal + "]");
        }
        pos += retVal.length() + 1;  //+1 next time start read from next line.
        return retVal;
    }

    @Override
    public void close() throws IOException {
        randomAccessFile.close();
    }
}
