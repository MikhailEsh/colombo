package main.utils;

import main.jms.connect.RemoteConnection;
import main.jms.connect.MQRemoteConnectionImpl;
import main.jms.receiver.stubs.MessageListenerStubImpl;
import main.schemas.srvprivateoperstate.*;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Properties;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */
public class TestUtils {



    private final String nameProperty = "../../mq-test.properties";
    private String textMessage = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><PrivateOperStateRq><RqUID>00000000000000000000000000000000</RqUID><RqTm>2001-12-17T09:30:47Z</RqTm><OperUID>String</OperUID><SPName>BP_ES</SPName><SystemId>SystemId</SystemId><OperDate>1967-08-13</OperDate><OperType>FullResult</OperType></PrivateOperStateRq>";
    private String testXSD = "../../../xsdTest.xsd";

    public Properties prepareProperties()
    {
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream(nameProperty);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return properties;
    }

    public RemoteConnection getMqRemoteConnection(Properties properties) throws IOException, JMSException, InterruptedException {
        MessageListener messageListener = new MessageListenerStubImpl();
        RemoteConnection remoteConnection = new MQRemoteConnectionImpl();
        remoteConnection.connect();
        remoteConnection.runConnectListner(messageListener);

        return remoteConnection;
    }

    public BytesMessage getTestBytesMessage() throws IOException, JMSException, InterruptedException {
        RemoteConnection remoteConnection = new MQRemoteConnectionImpl();
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream(nameProperty);
        properties.load(inputStream);
        remoteConnection.connect();
        BytesMessage  testMessage = remoteConnection.getSession().createBytesMessage();
        testMessage.writeBytes(textMessage.getBytes());
        testMessage.reset();
        return testMessage;
    }

    public String getTextMessage() {
        return this.textMessage;
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

    /*private BankInfoType getTestBankInfoType()
    {
        BankInfoType bankInfoType = new BankInfoType();
        bankInfoType.setBranchId("setBranchId");
        bankInfoType.setAgencyId("setAgencyId");
        bankInfoType.setRegionId("setRegionId");
        bankInfoType.setRbTbBrchId("setRbTbBrchId");
        bankInfoType.setRbBrchId("setRbBrchId");
        return bankInfoType;
    }*/

    private XMLGregorianCalendar getXMLGregorianCalendar(String dateTime) throws ParseException, DatatypeConfigurationException {
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTime);
    }
}
