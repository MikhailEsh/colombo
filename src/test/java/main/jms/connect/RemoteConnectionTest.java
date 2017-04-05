package main.jms.connect; /**
 * Created by sbt-eshtokin-ml on 29.03.2017.
 */
import org.junit.Test;
import main.utils.TestUtils;

import javax.jms.JMSException;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import java.io.IOException;
import java.util.Properties;


public class RemoteConnectionTest {

    private TestUtils testUtils = new TestUtils();



    @Test
    public void call() throws IOException, JMSException, InterruptedException {

            Properties properties = testUtils.prepareProperties();
            RemoteConnection remoteConnection = testUtils.getMqRemoteConnection(properties);
            QueueSession session = remoteConnection.getSession();
            if (session == null)
                throw new JMSException("Сессия неактивна, проверьте, есть ли подключение");
            QueueSender mqQueueSender = remoteConnection.getSender();
            if (mqQueueSender == null)
                throw new JMSException("Отправитель не активен, проверьте, есть ли подключение");
            System.out.println(this.getClass() + " SUCCES");

    }





}
