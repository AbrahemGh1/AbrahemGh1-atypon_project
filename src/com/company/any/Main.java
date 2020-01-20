package com.company.any;

import com.company.Config;
import com.company.Script;
import com.company.test.fileUploader;

import java.io.IOException;
import java.nio.file.Paths;


public class Main {
    static String host = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        //Java object. We will convert it to XML.
        Config config = new Config();


        //Method which uses JAXB to convert object to XML
        xml.jaxObjectToXML(config);


        Script.executeCommand("/home/abrahem/IdeaProjects/untitled3/src/runmapper.sh");

        new fileUploader(host, 2022).uploadFile(Paths.get("/home/abrahem/IdeaProjects/untitled3/Config.xml"));

        ResourceManager resourceManager = new ResourceManager();


    }
}

