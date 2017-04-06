package main.jms.connect;

import com.ibm.mq.jms.*;
import main.logger.service.SystemLog;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.jms.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
@Primary
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MQRemoteConnectionImpl implements RemoteConnection {

    private String host;
    private int port;
    private String queueManager;
    private String channel;
    private int transportType = 1;
    private String inputQueueName;
    private String outputQueueName;
    private MQQueueSession session;
    private MQQueueConnection connection;

    private MQQueueConnectionFactory connectionFactory;
    private MQQueueSender sender = null;
    private MQQueueReceiver receiver = null;
    private MQQueue inputQueue;
    private MQQueue outputQueue;

    private MessageConsumer consumer = null;

    static private final String nameProperty = "../../../mq.properties";

    private static final String RQ_UID = "123456789";
    private static final String OPER_UID = "223456789";


    public MQRemoteConnectionImpl(){
        connectionFactory = new MQQueueConnectionFactory();
    };

    public void sendRequest(String request) throws JMSException {
        TextMessage message = session.createTextMessage(request);
        sender.send(message);
    }

    public void runConnectListner(MessageListener messageListener) throws JMSException, InterruptedException {
        if (session.isEmpty())
            throw new JMSException("Сессия неактивна, проверьте, есть ли подключение");
        consumer.setMessageListener(messageListener);
        connection.start();
    }

    private void setConnectionSetting(Properties properties) throws IOException, JMSException {
                this.host = properties.getProperty("host");
                this.port =  Integer.parseInt(properties.getProperty("port"));
                this.queueManager = properties.getProperty("queueManager");
                this.channel = properties.getProperty("channel");
                this.transportType = Integer.parseInt(properties.getProperty("transportType"));
                this.inputQueueName = properties.getProperty("inQueue");
                this.outputQueueName = properties.getProperty("outQueue");

                connectionFactory.setHostName(host);
                connectionFactory.setPort(this.port);
                connectionFactory.setTransportType(this.transportType);
                connectionFactory.setQueueManager(queueManager);
                connectionFactory.setChannel(channel);
    }



    private void connectRun() throws JMSException, InterruptedException
    {
        connection = (MQQueueConnection) connectionFactory.createQueueConnection();
        session = (MQQueueSession) connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        inputQueue = (MQQueue) session.createQueue(inputQueueName);
        outputQueue = (MQQueue) session.createQueue(outputQueueName);
        sender = (MQQueueSender) session.createSender(outputQueue);
        receiver = (MQQueueReceiver) session.createReceiver(inputQueue);
        Destination destination = session.createQueue(inputQueueName);
        consumer = session.createConsumer(destination);
    }

    private Properties loadProperties()
    {
        Properties properties = new Properties();
        try {
            InputStream inputStream = getClass().getResourceAsStream(nameProperty);
            properties.load(inputStream);
        } catch (IOException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }
        return properties;
    }

    public void connect(){
        try {
            Properties properties = loadProperties();
            setConnectionSetting(properties);
            connectRun();
        }  catch (IOException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        } catch (JMSException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        } catch (InterruptedException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }
    }

    public Session getSession() {
        return this.session;
    }
}