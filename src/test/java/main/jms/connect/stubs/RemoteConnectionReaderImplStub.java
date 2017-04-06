package main.jms.connect.stubs;

import main.jms.connect.RemoteConnectionReader;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.QueueSender;
import javax.jms.QueueSession;

/**
 * Created by sbt-eshtokin-ml on 05.04.2017.
 */
public class RemoteConnectionReaderImplStub implements RemoteConnectionReader {
    public void connect() throws JMSException, InterruptedException {

    }

    public void runConnectListner(MessageListener messageListener) throws JMSException, InterruptedException {

    }
}
