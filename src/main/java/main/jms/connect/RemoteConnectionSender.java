package main.jms.connect;

import javax.jms.JMSException;

/**
 * Created by sbt-eshtokin-ml on 06.04.2017.
 */
public interface RemoteConnectionSender {
    public void connect() throws JMSException, InterruptedException;
    public void sendRequest(String request) throws JMSException;
}
