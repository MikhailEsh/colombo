package main.jms.common;

import main.logger.entity.Log;
import org.xml.sax.SAXException;

import javax.jms.Message;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by sbt-eshtokin-ml on 29.03.2017.
 */
public interface MessageWrapper {

    public byte[] getBodyBytes();
    public String getBodyString();
    public void setMessage(Message message);
}
