package main.jms.connect.stubs;

import main.jms.connect.RemoteConnectionSender;

import javax.jms.JMSException;

/**
 * Created by sbt-eshtokin-ml on 06.04.2017.
 */
public class RemoteConnectionSenderImplStub implements RemoteConnectionSender {
    public void connect() throws JMSException, InterruptedException {

    }

    public void sendRequest(String request) throws JMSException {

    }
}
