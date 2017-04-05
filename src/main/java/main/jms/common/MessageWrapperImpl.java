package main.jms.common;

import main.logger.service.SystemLog;
import org.springframework.stereotype.Component;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import java.nio.charset.Charset;

/**
 * Created by sbt-eshtokin-ml on 28.03.2017.
 */
@Component
public class MessageWrapperImpl implements MessageWrapper {

    private String bodyString;
    private byte[] bodyBytes;
    private String charset;
    private Message message;

    private byte[] getMessageBodyFromMessage(Message message) throws JMSException {
        byte[] bytes = null;
        if (message instanceof BytesMessage) {
            System.out.println("BytesMessage");
            bytes = new byte[(int) ((BytesMessage) message).getBodyLength()];
            ((BytesMessage) message).readBytes(bytes);
        } else  if (message instanceof TextMessage) {
            System.out.println("TextMessage");
        }
        return bytes;
    }


    public byte[] getBodyBytes() {
        return bodyBytes;
    }

    public String getBodyString() {
        return bodyString;
    }

    public void setMessage(Message message) {
        this.message = message;
        try {
            this.bodyBytes = getMessageBodyFromMessage(message);
            this.charset = message.getStringProperty("JMS_IBM_Character_Set");
            if (this.charset == null) this.charset = "UTF-8";
            this.bodyString = new String(bodyBytes, Charset.forName(charset));
        } catch (JMSException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }
    }
}
