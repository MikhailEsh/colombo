package main.jms.common.stubs;

import main.jms.common.MarshallingObject;
import main.schemas.srvprivateoperstate.PrivateOperStateRq;
import main.utils.TestDatas;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */
public class MarshallingObjectImplStub implements MarshallingObject {
    public Object getObject(byte[] bodyBytes, Class c) throws JAXBException {
        PrivateOperStateRq privateOperStateRq = null;
        try {
            privateOperStateRq = (new TestDatas()).getTestPrivateOperStateRq();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return privateOperStateRq;
    }

    public String getXML(Object inputObject) throws JAXBException {
        return null;
    }
}
