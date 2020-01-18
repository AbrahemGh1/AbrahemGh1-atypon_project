package com.company;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Config {
    private ReducerInfo reducerInfo = new ReducerInfo();
    private MapperInfo mapperInfo = new MapperInfo();
    //add
    //inputFile
    //Mapper class File
    //Reducer class file
    //output file



    public MapperInfo getMapperInfo() {
        return mapperInfo;
    }

    public void setMapperInfo(MapperInfo mapperInfo) {
        this.mapperInfo = mapperInfo;
    }

    public ReducerInfo getReducerInfo() {
        return reducerInfo;
    }

    public void setReducerInfo(ReducerInfo reducerInfo) {
        this.reducerInfo = reducerInfo;
    }


}

@XmlRootElement
class MapperInfo {
    private int mapperNumber = 2;

    public int getMapperNumber() {
        return mapperNumber;
    }

    public void setMapperNumber(int mapperNumber) {
        this.mapperNumber = mapperNumber;
    }

    public int getThreadsNumber() {
        return threadsNumber;
    }

    public void setThreadsNumber(int threadsNumber) {
        this.threadsNumber = threadsNumber;
    }

    private int threadsNumber = 2;
}

@XmlRootElement
class ReducerInfo {
    private int reducerNumber = 2;
    private int threadsNumber = 2;


    public int getReducerNumber() {
        return reducerNumber;
    }

    public void setReducerNumber(int reducerNumber) {
        this.reducerNumber = reducerNumber;
    }

    public int getThreadsNumber() {
        return threadsNumber;
    }

    public void setThreadsNumber(int threadsNumber) {
        this.threadsNumber = threadsNumber;
    }

}
