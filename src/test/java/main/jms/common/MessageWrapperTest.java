package main.jms.common;

import main.logger.entity.Log;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import main.utils.TestUtils;

import javax.jms.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by sbt-eshtokin-ml on 29.03.2017.
 */
public class MessageWrapperTest {





    private TestUtils testUtils = new TestUtils();


    @Before
    public void init() {
    }

    @Test
    public void call() throws ParserConfigurationException, SAXException, InterruptedException, JMSException, IOException {
        MessageWrapper messageWrapper = new MessageWrapperImpl();
        messageWrapper.setMessage(testUtils.getTestBytesMessage());
        String bodyString = messageWrapper.getBodyString();
        if (!bodyString.equals(testUtils.getTextMessage())) throw new AssertionError();
        System.out.println(this.getClass() + " SUCCES");
    }



    private boolean compareLogEntity(Log firstLog, Log secondLog)
    {
        if (!firstLog.getAdapter().equals(secondLog.getAdapter())) return false;
        if (!firstLog.getRqUid().equals(secondLog.getRqUid())) return false;
        if (!firstLog.getRqTime().equals(secondLog.getRqTime())) return false;
        if (!firstLog.getStatus().equals(secondLog.getStatus())) return false;
        if (!firstLog.getBody().equals(secondLog.getBody())) return false;
        return true;
    }
}
