package main.jms.common;

import main.schemas.srvprivateoperstate.PrivateOperStateRq;
import main.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;

/**
 * Created by sbt-eshtokin-ml on 31.03.2017.
 */
public class MarshallingObjectTest {

    private TestUtils testUtils;
    private byte[] bodyBytes;
    private Class testClass;
    private PrivateOperStateRq sampleObject;
    private String sampleXML;




    @Before
    public void init() throws ParseException, DatatypeConfigurationException {
        testUtils = new TestUtils();
        bodyBytes = testUtils.getTextMessage().getBytes();
        testClass = PrivateOperStateRq.class;
        sampleObject = testUtils.getTestPrivateOperStateRq();
        sampleXML = testUtils.getTextMessage();

    }

    @Test
    public void getObject() throws Exception {
        MarshallingObject marshallingObject = new MarshallingObjectImpl();
        PrivateOperStateRq testObject = (PrivateOperStateRq)marshallingObject.getObject(bodyBytes, testClass);
        comparePrivateOperStateRq(testObject, sampleObject);
    }

    @Test
    public void getXML() throws Exception {
        MarshallingObject marshallingObject = new MarshallingObjectImpl();
        String testXML = marshallingObject.getXML(sampleObject);
        if (!testXML.equals(sampleXML)) throw new Exception();
    }

    private void comparePrivateOperStateRq(PrivateOperStateRq first, PrivateOperStateRq second) throws Exception {
        if (!first.getRqUID().equals(second.getRqUID())) throw new Exception();
        if (!first.getRqTm().equals(second.getRqTm())) throw new Exception();
        if (!first.getOperUID().equals(second.getOperUID())) throw new Exception();
        if (!(first.getSPName() == second.getSPName())) throw new Exception();
        if (!first.getSystemId().equals(second.getSystemId())) throw new Exception();
        if (!first.getOperDate().equals(second.getOperDate())) throw new Exception();
        if (!first.getOperType().equals(second.getOperType())) throw new Exception();
    }



}