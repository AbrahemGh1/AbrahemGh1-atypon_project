package com.company.any;

import com.company.Config;

import java.io.*;


public class Main {
    static String host = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        //Java object. We will convert it to XML.
        Config config = new Config();


        //Method which uses JAXB to convert object to XML
        xml.jaxObjectToXML(config);


        //Script.executeCommand("/home/abrahem/IdeaProjects/untitled3/src/createContainer.sh");

        //new fileUploader(host,2022).uploadFile(Paths.get("/home/abrahem/IdeaProjects/untitled3/Config.xml"));

        ResourceManager resourceManager = new ResourceManager();

 //       TaskHub Hub  = InputSplit.getSplits();


    }

}
//
//class inputSplit {
//    String[] getSplits() {
//        return null;
//    }
//
//
//}
//interface RecordReader<K ,V>{
//    Record getNextRecord();
//}


//public class FileSplit extends InputSplit implements Writable {
//    private Path file;
//    private long start;
//    private long length;
//    private String[] hosts;
//
//    public FileSplit() {}
//
//    /** Constructs a com.company.split with host information
//     *
//     * @param file the file name
//     * @param start the position of the first byte in the file to process
//     * @param length the number of bytes in the file to process
//     * @param hosts the list of hosts containing the block, possibly null
//     */
//    public FileSplit(Path file, long start, long length, String[] hosts) {
//        this.file = file;
//        this.start = start;
//        this.length = length;
//        this.hosts = hosts;
//    }
//
//    /** The file containing this com.company.split's data. */
//    public Path getPath() { return file; }
//
//    /** The position of the first byte in the file to process. */
//    public long getStart() { return start; }
//
//    /** The number of bytes in the file to process. */
//    @Override
//    public long getLength() { return length; }
//
//    @Override
//    public String toString() { return file + ":" + start + "+" + length; }
//
//    ////////////////////////////////////////////
//    // Writable methods
//    ////////////////////////////////////////////
//
//    @Override
//    public void write(DataOutput out) throws IOException {
//        Text.writeString(out, file.toString());
//        out.writeLong(start);
//        out.writeLong(length);
//    }
//
//    @Override
//    public void readFields(DataInput in) throws IOException {
//        file = new Path(Text.readString(in));
//        start = in.readLong();
//        length = in.readLong();
//        hosts = null;
//    }
//
//    @Override
//    public String[] getLocations() throws IOException {
//        if (this.hosts == null) {
//            return new String[]{};
//        } else {
//            return this.hosts;
//        }
//    }
//}


