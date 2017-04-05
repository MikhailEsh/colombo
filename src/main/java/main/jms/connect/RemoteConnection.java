package main.jms.connect;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.QueueSender;
import javax.jms.QueueSession;

/**
 * Created by sbt-eshtokin-ml on 27.03.2017.
 */

public interface RemoteConnection {
    public void connect() throws JMSException, InterruptedException;
    public void runConnectListner(MessageListener messageListener) throws JMSException, InterruptedException;
    public QueueSession getSession();
    public QueueSender getSender();



}
