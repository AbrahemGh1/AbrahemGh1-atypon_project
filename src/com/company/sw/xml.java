package com.company.sw;

import com.company.Config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

public class xml {
    public static void jaxObjectToXML(Config config) {
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

    static Config XMLToJaxbObject(String path) {
        File xmlFile = new File(path);
        Config config = null;

        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Config.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            config = (Config) jaxbUnmarshaller.unmarshal(xmlFile);

            System.out.println(config);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return config;
    }
}
