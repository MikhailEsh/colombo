package main.jms.common.stubs;

import main.jms.common.MessageWrapper;
import main.logger.entity.Log;
import org.xml.sax.SAXException;

import javax.jms.Message;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */
public class MessageWrapperTestImpl implements MessageWrapper {
    public byte[] getBodyBytes() {
        return new byte[0];
    }

    public String getBodyString() {
        return null;
    }

    public Log prepareLogEntity() throws ParserConfigurationException, SAXException {
        return null;
    }

    public void setMessage(Message message) {

    }
}
