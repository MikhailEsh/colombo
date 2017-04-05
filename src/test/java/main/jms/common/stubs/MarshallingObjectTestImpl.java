package main.jms.common.stubs;

import main.jms.common.MarshallingObject;

import javax.xml.bind.JAXBException;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */
public class MarshallingObjectTestImpl implements MarshallingObject {
    public Object getObject(byte[] bodyBytes, Class c) throws JAXBException {
        return new Object();
    }

    public String getXML(Object inputObject) throws JAXBException {
        return null;
    }
}
