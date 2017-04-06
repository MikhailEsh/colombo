package main.jms.connect;

import javax.jms.*;

/**
 * Created by sbt-eshtokin-ml on 27.03.2017.
 */

public interface RemoteConnectionReader {
    public void connect() throws JMSException, InterruptedException;
    public void runConnectListner(MessageListener messageListener) throws JMSException, InterruptedException;


}
