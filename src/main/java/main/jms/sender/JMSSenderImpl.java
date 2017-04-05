package main.jms.sender;

import main.jms.common.MarshallingObject;
import main.jms.connect.RemoteConnection;
import main.logger.service.LogService;
import main.logger.service.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.jms.JMSException;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by sbt-eshtokin-ml on 29.03.2017.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class JMSSenderImpl implements JMSSender{

    @Autowired
    private RemoteConnection remoteConnection;

    @Autowired
    private MarshallingObject marshallingObject;

    @Autowired
    private LogService logService;

    public void sendRequest(Object request )
    {
        try {
            String xmlRs = marshallingObject.getXML(request);
            logService.saveLog(xmlRs, this.getClass());
            QueueSession session = remoteConnection.getSession();
            System.out.println("QueueSession session = remoteConnection.getSession();");
                TextMessage message = session.createTextMessage(xmlRs);
                System.out.println("TextMessage message = session.createTextMessage(xmlRs);");
                if (session == null)
                    throw new JMSException("Сессия неактивна, проверьте, есть ли подключение");
                remoteConnection.getSender().send(message);
            System.out.println("Response succes was sent");
        } catch (JMSException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        } catch (JAXBException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        } catch (SAXException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        } catch (ParserConfigurationException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }
    }
}
