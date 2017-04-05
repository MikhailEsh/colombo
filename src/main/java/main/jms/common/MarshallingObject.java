package main.jms.common;

import javax.xml.bind.JAXBException;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */
public interface MarshallingObject {

    public Object getObject(byte[] bodyBytes, Class c) throws JAXBException;
    public String getXML(Object inputObject) throws JAXBException;
}
