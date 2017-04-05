package main.jms.receiver;

import main.Utils;
import main.jms.common.MessageWrapper;
import main.jms.common.MarshallingObject;
import main.jms.sender.JMSSender;
import main.logger.service.LogService;
import main.logger.service.SystemLog;
import main.schemas.srvprivateoperstate.PrivateOperStateRq;
import main.schemas.srvprivateoperstate.PrivateOperStateRs;
import main.schemas.srvprivateoperstate.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.util.concurrent.BlockingQueue;

/**
 * Created by sbt-eshtokin-ml on 31.03.2017.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MessageProcessorSingleImpl implements MessageProcessor{

    private BlockingQueue<MessageWrapper> queueMsMessageWrappers;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private JMSSender jmsSender;

    @Autowired
    private LogService logService;

    @Autowired
    private MarshallingObject marshallingObject;

    //private final int nameThread;

    /*public MessageProcessorSingleImpl(){
        synchronized (MessageProcessorSchedulerImpl.counterThreads) {
            this.nameThread = MessageProcessorSchedulerImpl.counterThreads.incrementAndGet();
        }
    };*/

    /*@PostConstruct
    public void init()
    {
        this.queueMsMessageWrappers = (BlockingQueue<MessageWrapper>)applicationContext.getBean("getQueueMsgWrapForMsgProcessor");
    }*/




    public void execMessage(MessageWrapper messageWrapper)
    {
        PrivateOperStateRq privateOperStateRq = null;
        try {
            //Utils.TimeWaite();
            System.out.println("MessageProcessorSingleImpl get");
            privateOperStateRq = (PrivateOperStateRq) marshallingObject.getObject(messageWrapper.getBodyBytes(), PrivateOperStateRq.class);
            System.out.println("marshallingObject success");
            logService.saveLog(privateOperStateRq, this.getClass());
            System.out.println("marshallingObject saveLog success");
            PrivateOperStateRs privateOperStateRs = transformToRs(privateOperStateRq);
            System.out.println("transformToRs success");
            logService.saveLog(privateOperStateRs, this.getClass());
            System.out.println("Log Saved succes");
            jmsSender.sendRequest(privateOperStateRs);
        } catch (JAXBException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        } catch (SAXException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        } catch (ParserConfigurationException e) {
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

    /*public void run() {
        while (true) {
            try {
                MessageWrapper messageWrapper = queueMsMessageWrappers.take();
                System.out.println("this.queueMsMessageWrappers.take " + nameThread);
                execMessageSingleThread(messageWrapper);
            } catch (InterruptedException e) {
                SystemLog.SaveErrorLog(this.getClass(), e);
            }
        }
    }*/

    public void setQueueMsMessageWrappers(BlockingQueue<MessageWrapper> queueMsMessageWrappers) {
        this.queueMsMessageWrappers = queueMsMessageWrappers;
    }

    /*public int getNameThread() {
        return this.nameThread;
    }*/
}
