package com.company;

import com.company.test.fileUploader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    static String host = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        //Java object. We will convert it to XML.
        Config config = new Config();


        //Method which uses JAXB to convert object to XML
        jaxbObjectToXML(config);


        //Script.executeCommand("/home/abrahem/IdeaProjects/untitled3/src/createContainer.sh");

        new fileUploader(host,2022).uploadFile(Paths.get("/home/abrahem/IdeaProjects/untitled3/Config.xml"));
    }

    private static void jaxbObjectToXML(Config config) {
        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Print XML String to Console
            StringWriter sw = new StringWriter();

            //Write XML to StringWriter
            jaxbMarshaller.marshal(config, sw);

            //Verify XML Content
            String xmlContent = sw.toString();
            System.out.println(xmlContent);
            FileWriter myWriter = new FileWriter("Config.xml");
            myWriter.write(xmlContent);
            myWriter.close();


        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }
}
