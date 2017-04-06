package main.jms.connect; /**
 * Created by sbt-eshtokin-ml on 29.03.2017.
 */
import main.jms.receiver.stubs.MessageListenerStubImpl;
import org.junit.Test;

import javax.jms.JMSException;
import javax.jms.Session;


public class RemoteConnectionTest {


    @Test
    public void testMQRemoteConnection() throws InterruptedException, JMSException {

            RemoteConnection remoteConnection = new MQRemoteConnectionImpl();
            remoteConnection.connect();
            remoteConnection.runConnectListner(new MessageListenerStubImpl());
            Session session = remoteConnection.getSession();
            if (session == null ) throw new JMSException("Session empty");
            session.createTextMessage();
    }





}
