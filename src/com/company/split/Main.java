package com.company.split;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileSplit fs = new FileSplit(Paths.get("/home/abrahem/Desktop/input.txt"));
        List s = fs.getSplits();
        System.out.println("yes");
    }

}
