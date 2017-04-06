package main.jms.connect.stubs;

import main.jms.connect.RemoteConnection;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.QueueSender;
import javax.jms.QueueSession;

/**
 * Created by sbt-eshtokin-ml on 05.04.2017.
 */
public class RemoteConnectionImplStub implements RemoteConnection{
    public void connect() throws JMSException, InterruptedException {

    }

    public void runConnectListner(MessageListener messageListener) throws JMSException, InterruptedException {

    }

    public QueueSession getSession() {
        return null;
    }

    public QueueSender getSender() {
        return null;
    }

    public void sendRequest(String request) throws JMSException {

    }
}
