package main.logger.service.impl;

import main.logger.service.LogService;
import main.logger.service.SystemLog;
import main.schemas.srvprivateoperstate.PrivateOperStateRq;
import main.schemas.srvprivateoperstate.PrivateOperStateRs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sbt-eshtokin-ml on 06.04.2017.
 */

@Service
@Primary
@Repository
public class LogServiceSchedulerImpl  implements LogService{

    private final int countThread = 3;
    private final int maxLengthQueue = 5;

    private BlockingQueue<WrapperLog> queueLogsEntity;
    private ExecutorService executorService;

    static protected AtomicInteger counterThreads = new AtomicInteger(0);



    @Autowired
    private ApplicationContext applicationContext;

    public LogServiceSchedulerImpl()
    {
        this.queueLogsEntity = new ArrayBlockingQueue<WrapperLog>(maxLengthQueue);
        executorService = Executors.newCachedThreadPool();
    }


    @PostConstruct
    public void startFlow()
    {
        for (int i = 0; i < countThread; i++) {
            executorService.execute((Runnable)applicationContext.getBean("logServiceSingleImpl"));
        }
        executorService.shutdown();
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public BlockingQueue<WrapperLog> getQueueLogsEntity() {
        return queueLogsEntity;
    }

    public void saveLog(String bodyString, Class savedClass){
       WrapperLog wrapperLog = new WrapperLog(bodyString, savedClass);
        try {
            queueLogsEntity.put(wrapperLog);
        } catch (InterruptedException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }
    }

    public void saveLog(PrivateOperStateRq privateOperStateRq, Class savedClass) {
        WrapperLog wrapperLog = new WrapperLog(privateOperStateRq, savedClass);
        try {
            queueLogsEntity.put(wrapperLog);
        } catch (InterruptedException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }
    }

    public void saveLog(PrivateOperStateRs privateOperStateRs, Class savedClass){
        WrapperLog wrapperLog = new WrapperLog(privateOperStateRs, savedClass);
        try {
            queueLogsEntity.put(wrapperLog);
        } catch (InterruptedException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }
    }
}
