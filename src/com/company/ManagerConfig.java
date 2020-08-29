package com.company;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ManagerConfig {
    private ReducerConfig reducerInfo = new ReducerConfig();
    private MapperConfig mapperInfo = new MapperConfig();



    public MapperConfig getMapperInfo() {
        return mapperInfo;
    }

    public void setMapperInfo(MapperConfig mapperInfo) {
        this.mapperInfo = mapperInfo;
    }

    public ReducerConfig getReducerInfo() {
        return reducerInfo;
    }

    public void setReducerInfo(ReducerConfig reducerInfo) {
        this.reducerInfo = reducerInfo;
    }


}

@XmlRootElement
class MapperConfig {
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
class ReducerConfig {
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
