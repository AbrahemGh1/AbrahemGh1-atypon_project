import com.company.Config;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ResourceManager {
    private Config config;

    public ResourceManager() {
        config = xml.factory("config.xml");
    }
}

class xml {
    static Config factory(String path) {
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
