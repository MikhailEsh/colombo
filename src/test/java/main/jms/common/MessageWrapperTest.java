package main.jms.common;

import com.ibm.jms.JMSBytesMessage;
import main.logger.entity.Log;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;
import main.utils.TestDatas;

import javax.jms.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by sbt-eshtokin-ml on 29.03.2017.
 */
public class MessageWrapperTest {





    private TestDatas testDatas = new TestDatas();

    @Test
    public void call() throws ParserConfigurationException, SAXException, InterruptedException, JMSException, IOException {
        //Проблема с подготовкой тестового Message
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
