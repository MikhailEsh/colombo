package main.jms.connect;

import javax.jms.*;

/**
 * Created by sbt-eshtokin-ml on 27.03.2017.
 */

public interface RemoteConnection {
    public void connect() throws JMSException, InterruptedException;
    public void runConnectListner(MessageListener messageListener) throws JMSException, InterruptedException;
    public void sendRequest(String request) throws JMSException;
    public Session getSession();

}
