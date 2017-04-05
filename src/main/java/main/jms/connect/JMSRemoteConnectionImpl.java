package main.jms.connect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by sbt-eshtokin-ml on 04.04.2017.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class JMSRemoteConnectionImpl implements RemoteConnection {

    private static final Logger log = LogManager.getLogger(JMSRemoteConnectionImpl.class);
    private final String jndiQueueConFac = "jms/MyQueueConnectionFactory";
    private final String jndiQueueIn = "jms/MyQueueIn";
    private final String jndiQueueOut = "jms/MyQueueOut";


    private QueueSession session;
    private MessageConsumer messageConsumer;
    private QueueConnection queueConnection;
    private QueueSender sender;

    public void connect()
    {
        Context ctx = null;
        try {
            ctx = (Context) new InitialContext().lookup("java:comp/env");
            System.out.println("Context JMS is looked up");
            QueueConnectionFactory queueConnectionFactory =(QueueConnectionFactory)ctx.lookup(jndiQueueConFac);
            System.out.println("QueueConnectionFactory JMS is looked up");
            queueConnection = queueConnectionFactory.createQueueConnection();
            System.out.println("QueueConnection JMS is created");
            session = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            System.out.println("QueueSession JMS is created");
            connectToReceiver(ctx);
            connectToSender(ctx);
        } catch (Exception e) {
            log.error("MISHA",e);
        }
    }

    private void connectToReceiver(Context ctx) throws NamingException, JMSException {
        Queue queue = (Queue)ctx.lookup(jndiQueueIn);
        System.out.println("Queue IN JMS is looked up");
        messageConsumer = session.createConsumer(queue);
        System.out.println("Receiver JMS is looked up");
    }

    private void connectToSender(Context ctx) throws NamingException, JMSException {
        Queue queue = (Queue) ctx.lookup(jndiQueueOut);
        System.out.println("Queue OUT JMS is looked up");
        sender = session.createSender(queue);
        System.out.println("Sender JMS is created");
    }


    public void runConnectListner(MessageListener messageListener) throws JMSException, InterruptedException {
        if (messageConsumer == null) throw new JMSException("Потребитель не определен");
        messageConsumer.setMessageListener(messageListener);
        queueConnection.start();
        System.out.println("QueueConnection JMS is started");
    }

    public QueueSession getSession() {
        return session;
    }

    public QueueSender getSender() {
        return sender;
    }
}
