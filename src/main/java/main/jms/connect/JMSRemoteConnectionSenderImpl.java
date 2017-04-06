package main.jms.connect;

import main.logger.service.SystemLog;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by sbt-eshtokin-ml on 06.04.2017.
 */

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class JMSRemoteConnectionSenderImpl implements RemoteConnectionSender{

    private final String jndiQueueConFac = "jms/MyQueueConnectionFactory";
    private final String jndiQueueOut = "jms/MyQueueOut";
    private final String env = "java:comp/env";

    private QueueSender sender;
    private QueueSession session;
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
            connectToSender(ctx);
        } catch (Exception e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }
    }

    private void connectToSender(Context ctx) throws NamingException, JMSException {
        Queue queue = (Queue) ctx.lookup(jndiQueueOut);
        System.out.println("Queue OUT JMS is looked up");
        sender = session.createSender(queue);
        System.out.println("Sender JMS is created");
    }

    public void sendRequest(String request) throws JMSException {
        TextMessage message = session.createTextMessage(request);
        sender.send(message);
    }
}
