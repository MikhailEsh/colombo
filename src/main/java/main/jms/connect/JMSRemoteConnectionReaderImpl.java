package main.jms.connect;

import main.logger.service.SystemLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
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
public class JMSRemoteConnectionReaderImpl implements RemoteConnectionReader {

    private final String jndiQueueConFac = "jms/MyQueueConnectionFactory";
    private final String jndiQueueIn = "jms/MyQueueIn";
    private final String env = "java:comp/env";


    private QueueSession session;
    private MessageConsumer messageConsumer;
    private QueueConnection queueConnection;

    public void connect()
    {
        Context ctx = null;
        try {
            ctx = (Context) new InitialContext().lookup(env);
            System.out.println("Context JMS is looked up");
            QueueConnectionFactory queueConnectionFactory =(QueueConnectionFactory)ctx.lookup(jndiQueueConFac);
            System.out.println("QueueConnectionFactory JMS is looked up");
            queueConnection = queueConnectionFactory.createQueueConnection();
            System.out.println("QueueConnection JMS is created");
            session = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            System.out.println("QueueSession JMS is created");
            connectToReceiver(ctx);
        } catch (Exception e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }
    }

    private void connectToReceiver(Context ctx) throws NamingException, JMSException {
        Queue queue = (Queue)ctx.lookup(jndiQueueIn);
        System.out.println("Queue IN JMS is looked up");
        messageConsumer = session.createConsumer(queue);
        System.out.println("Receiver JMS is looked up");
    }

    public void runConnectListner(MessageListener messageListener) throws JMSException, InterruptedException {
        if (messageConsumer == null) throw new JMSException("Потребитель не определен");
        messageConsumer.setMessageListener(messageListener);
        queueConnection.start();
        System.out.println("QueueConnection JMS is started");
    }
}
