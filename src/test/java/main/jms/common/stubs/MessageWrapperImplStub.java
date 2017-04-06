package main.jms.common.stubs;

import main.jms.common.MessageWrapper;
import main.logger.config.DataConfigTest;
import main.logger.entity.Log;
import main.utils.TestDatas;
import org.xml.sax.SAXException;

import javax.jms.Message;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */
public class MessageWrapperImplStub implements MessageWrapper {

    private TestDatas testDatas = new TestDatas();

    public byte[] getBodyBytes() {
        return testDatas.getTestText().getBytes();
    }

    public String getBodyString() {
        return testDatas.getTestText();
    }

    public void setMessage(Message message) {

    }
}
