package main.utils;

import com.ibm.disthub2.impl.jms.BytesMessageImpl;
import main.jms.connect.RemoteConnection;
import main.jms.connect.MQRemoteConnectionImpl;
import main.jms.receiver.stubs.MessageListenerStubImpl;
import main.schemas.srvprivateoperstate.*;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */
public class TestDatas {



    private final String nameProperty = "../../mq-test.properties";
    private String testXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><PrivateOperStateRq><RqUID>00000000000000000000000000000000</RqUID><RqTm>2001-12-17T09:30:47Z</RqTm><OperUID>String</OperUID><SPName>BP_ES</SPName><SystemId>SystemId</SystemId><OperDate>1967-08-13</OperDate><OperType>FullResult</OperType></PrivateOperStateRq>";
    private String testXSD = "../../../SrvPrivateOperState001.xsd";

    public String getTestText() {
        return this.testXml;
    }

    public String getTestXSD() {
        return testXSD;
    }

    public PrivateOperStateRq getTestPrivateOperStateRq() throws ParseException, DatatypeConfigurationException {
        PrivateOperStateRq privateOperStateRq = new PrivateOperStateRq();
        privateOperStateRq.setRqUID("00000000000000000000000000000000");
        privateOperStateRq.setRqTm(getXMLGregorianCalendar("2001-12-17T09:30:47Z"));
        privateOperStateRq.setOperUID("String");
        privateOperStateRq.setSPName(SPNameType.BP_ES);
        privateOperStateRq.setSystemId("SystemId");
        privateOperStateRq.setOperDate(getXMLGregorianCalendar("1967-08-13"));
        privateOperStateRq.setOperType("FullResult");
        return  privateOperStateRq;
    }

    public PrivateOperStateRs getTestPrivateOperStateRs() throws ParseException, DatatypeConfigurationException {
        PrivateOperStateRs privateOperStateRs = new PrivateOperStateRs();
        privateOperStateRs.setRqUID("00000000000000000000000000000000");
        privateOperStateRs.setRqTm(getXMLGregorianCalendar("2001-12-17T09:30:47Z"));
        privateOperStateRs.setOperUID("setOperUID");
        StatusType statusType = new StatusType();
        statusType.setStatusCode(0);
        privateOperStateRs.setStatus(statusType);
        return privateOperStateRs;
    }

    private XMLGregorianCalendar getXMLGregorianCalendar(String dateTime) throws ParseException, DatatypeConfigurationException {
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTime);
    }

}
