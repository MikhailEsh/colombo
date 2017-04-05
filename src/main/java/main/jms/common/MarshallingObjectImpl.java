package main.jms.common;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringWriter;

/**
 * Created by sbt-eshtokin-ml on 27.03.2017.
 */

@Component
public class MarshallingObjectImpl implements MarshallingObject {

    public Object getObject(byte[] bodyBytes, Class requiredObject) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(requiredObject);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bodyBytes);
        Object object = unmarshaller.unmarshal(inputStream);
        return object;
    }

    public String getXML(Object inputObject) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(inputObject.getClass());
        Marshaller marshaller = context.createMarshaller();
        StringWriter result = new StringWriter();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.marshal(inputObject, result);
        return result.toString();
    }
}
