package main.jms.receiver;

import main.jms.common.MessageWrapper;
import main.logger.service.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sbt-eshtokin-ml on 03.04.2017.
 */
//@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MessageProcessorSchedulerImpl implements MessageProcessor{

    private final int countThread = 3;
    private final int maxLengthQueue = 5;

    @Autowired
    private ApplicationContext applicationContext;


    private BlockingQueue<MessageWrapper> queueMsgWrapForMsgProcessor;
    private ExecutorService executorService;


    static protected AtomicInteger counterThreads;

    public MessageProcessorSchedulerImpl()
    {
        counterThreads = new AtomicInteger(0);
        this.queueMsgWrapForMsgProcessor = new ArrayBlockingQueue<MessageWrapper>(maxLengthQueue);
        executorService = Executors.newCachedThreadPool();


    }

    /*@PostConstruct
    public void init()
    {
        for (int i = 0; i < countThread; i++) {
            MessageProcessorSingleImpl messageProcessorSingle = (MessageProcessorSingleImpl)applicationContext.getBean("messageProcessorSingleImpl");
            executorService.execute(messageProcessorSingle);
        }
        executorService.shutdown();
    }*/

    public void execMessage(MessageWrapper messageWrapperRq) {
        try {
            this.queueMsgWrapForMsgProcessor.put(messageWrapperRq);
        } catch (InterruptedException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public BlockingQueue<MessageWrapper> getQueueMsgWrapForMsgProcessor() {
        return queueMsgWrapForMsgProcessor;
    }
}
