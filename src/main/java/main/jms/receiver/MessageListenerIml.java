package main.jms.receiver;

import com.google.common.io.Resources;
import main.jms.common.MessageWrapper;
import main.jms.common.ValidateTools;
import main.logger.service.LogService;
import main.logger.service.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.jms.*;
import javax.xml.parsers.ParserConfigurationException;
import java.net.URL;

/**
 * Created by sbt-eshtokin-ml on 27.03.2017.
 */

@Component("messageListener")
public class MessageListenerIml implements MessageListener {

    private URL currentSchemaURL = Resources.getResource(this.getClass(),
            "../../../SrvPrivateOperState001.xsd");

    public MessageListenerIml() {};

    public MessageListenerIml(ValidateTools validateTools, LogService serviceLog, MessageWrapper messageWrapper)
    {
        this.validateTools = validateTools;
        this.serviceLog = serviceLog;
        this.messageWrapper = messageWrapper;
    }

    @Autowired
    private ValidateTools validateTools;

    @Autowired
    private LogService serviceLog;

    @Autowired
    MessageWrapper messageWrapper;

    @Autowired
    MessageProcessor messageProcessor;


    public void onMessage(Message message) {
        System.out.println("Message received from queue");
        try {
            messageWrapper.setMessage(message);
            if (messageWrapper.getBodyBytes() == null) throw new NullPointerException();
            serviceLog.saveLog(messageWrapper.getBodyString(), this.getClass());
            validateTools.validateByURL(currentSchemaURL, messageWrapper.getBodyBytes());
            System.out.println("Validate succes");
            System.out.println("MessageProcessor " + messageProcessor);
            messageProcessor.execMessage(messageWrapper);
        } catch (NullPointerException e ) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }

    }
}
