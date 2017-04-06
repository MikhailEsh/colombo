package main.jms.receiver;

import main.Utils;
import main.jms.common.MessageWrapper;
import main.jms.common.MarshallingObject;
import main.jms.connect.RemoteConnectionSender;
import main.logger.service.LogService;
import main.logger.service.SystemLog;
import main.schemas.srvprivateoperstate.PrivateOperStateRq;
import main.schemas.srvprivateoperstate.PrivateOperStateRs;
import main.schemas.srvprivateoperstate.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.util.concurrent.BlockingQueue;

/**
 * Created by sbt-eshtokin-ml on 31.03.2017.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MessageProcessorSingleImpl implements MessageProcessor, Runnable{

    private BlockingQueue<MessageWrapper> queueMsMessageWrappers;

    public MessageProcessorSingleImpl(LogService logService, MarshallingObject marshallingObject, RemoteConnectionSender remoteConnectionSender){
        this.logService = logService;
        this.marshallingObject = marshallingObject;
        this.remoteConnectionSender = remoteConnectionSender;
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private LogService logService;

    @Autowired
    private MarshallingObject marshallingObject;

    @Autowired
    volatile private RemoteConnectionSender remoteConnectionSender;

    private int nameThread;

    public MessageProcessorSingleImpl(){

    };

    @PostConstruct
    public void init() {
        this.queueMsMessageWrappers = (BlockingQueue<MessageWrapper>)applicationContext.getBean("getQueueMsgWrapForMsgProcessor");
        this.nameThread = MessageProcessorSchedulerImpl.counterThreads.incrementAndGet();
    }




    public void execMessage(MessageWrapper messageWrapper)
    {
        PrivateOperStateRq privateOperStateRq = null;
        try {
            Utils.TimeWaite();
            privateOperStateRq = (PrivateOperStateRq) marshallingObject.getObject(messageWrapper.getBodyBytes(), PrivateOperStateRq.class);
            logService.saveLog(privateOperStateRq, this.getClass());
            PrivateOperStateRs privateOperStateRs = transformToRs(privateOperStateRq);
            logService.saveLog(privateOperStateRs, this.getClass());
            String xmlRs = marshallingObject.getXML(privateOperStateRs);
            logService.saveLog(xmlRs, this.getClass());
            synchronized (remoteConnectionSender) {
                remoteConnectionSender.sendRequest(xmlRs);
            }
            System.out.println("Message sent");
        } catch (JAXBException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        } catch (JMSException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }
    }

    private PrivateOperStateRs transformToRs(PrivateOperStateRq privateOperStateRq)
    {
        PrivateOperStateRs privateOperStateRs = new PrivateOperStateRs();
        privateOperStateRs.setRqUID(privateOperStateRq.getRqUID());
        privateOperStateRs.setRqTm(privateOperStateRq.getRqTm());
        privateOperStateRs.setOperUID(privateOperStateRq.getOperUID());
        StatusType statusType = new StatusType();
        statusType.setStatusCode(0);
        privateOperStateRs.setStatus(statusType);
        return privateOperStateRs;
    }

    public void run() {
        while (true) {
            try {
                MessageWrapper messageWrapper = queueMsMessageWrappers.take();
                System.out.println("this.queueMsMessageWrappers.take " + nameThread);
                execMessage(messageWrapper);
            } catch (InterruptedException e) {
                SystemLog.SaveErrorLog(this.getClass(), e);
            }
        }
    }
}
