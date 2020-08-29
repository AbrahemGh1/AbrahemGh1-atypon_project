package com.company.sw;

import com.company.ManagerConfig;

public class ResourceManager {
    private ManagerConfig config;

    public ResourceManager() {
        config = xml.XMLToJaxbObject("/home/abrahem/IdeaProjects/untitled3/Config2.xml");
    }
}

