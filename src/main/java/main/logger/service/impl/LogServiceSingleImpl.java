package main.logger.service.impl;

import main.Utils;
import main.logger.entity.Log;
import main.logger.repository.LogEntityRepository;
import main.logger.service.LogService;
import main.logger.service.SystemLog;
import main.schemas.srvprivateoperstate.PrivateOperStateRq;
import main.schemas.srvprivateoperstate.PrivateOperStateRs;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */

@Service
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LogServiceSingleImpl implements LogService, Runnable {


    @Autowired
    private LogEntityRepository logEntityRepository;

    @Autowired
    private ApplicationContext applicationContext;

    private String nameThread;

    private BlockingQueue<WrapperLog> queueLogsEntity;

    @PostConstruct
    public void init() {
        this.queueLogsEntity = (BlockingQueue<WrapperLog>)applicationContext.getBean("getQueueLogsEntity");
        this.nameThread = this.getClass().getSimpleName() + " " + LogServiceSchedulerImpl.counterThreads.incrementAndGet();
    }

    public void run() {
        while (true) {

            WrapperLog messageWrapper = null;
            try {
                messageWrapper = queueLogsEntity.take();
                System.out.println("Logged by nameThread " + this.nameThread);
                Utils.TimeWaite();
                Object content = messageWrapper.getContent();
                Class savedClass = messageWrapper.getSavedClass();
                if (content instanceof String) {
                    saveLog((String)content, savedClass);
                } else if (content instanceof PrivateOperStateRq) {
                    saveLog((PrivateOperStateRq)content, savedClass);
                } else if (content instanceof PrivateOperStateRs) {
                    saveLog((PrivateOperStateRs)content, savedClass);
                } else throw new Exception("Type isn't allowed for Save");
            } catch (InterruptedException e) {
                SystemLog.SaveErrorLog(this.getClass(), e);
            } catch (Exception e) {
                SystemLog.SaveErrorLog(this.getClass(), e);
            }
        }
    }

    public void saveLog(String bodyString, Class savedClass){
        Log log = null;
        try {
            log = prepareLogEntity(bodyString, savedClass);
            logEntityRepository.saveAndFlush(log);
        } catch (ParserConfigurationException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        } catch (SAXException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }

    }

    public void saveLog(PrivateOperStateRq privateOperStateRq, Class savedClass){
        Log log = null;
        try {
            log = prepareLogEntity(privateOperStateRq, savedClass);
            logEntityRepository.saveAndFlush(log);
        } catch (ParserConfigurationException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        } catch (SAXException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }

    }

    public void saveLog(PrivateOperStateRs privateOperStateRs, Class savedClass) {
        Log log = prepareLogEntity(privateOperStateRs, savedClass);
        logEntityRepository.saveAndFlush(log);
    }


    private Element getRootElement(String bodyString)
    {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = null;
        try {
            document = (Document)saxBuilder.build(new ByteArrayInputStream(bodyString.getBytes()));
        } catch (JDOMException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        } catch (IOException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }
        Element rootElement = document.getRootElement();
        return rootElement;
    }

    private Log prepareLogEntity(PrivateOperStateRs privateOperStateRs, Class savedClass)
    {
        Log log = new Log();
        log.setBody(privateOperStateRs.toString());
        log.setStatus("SUCCES");
        log.setOperationName(privateOperStateRs.getClass().getSimpleName());
        log.setRqUid(privateOperStateRs.getRqUID());
        log.setRqTime(privateOperStateRs.getRqTm().toString());
        log.setPutTime();
        log.setAdapter(savedClass.getSimpleName());
        return log;
    }

    private Log prepareLogEntity(String bodyString, Class savedClass) throws ParserConfigurationException, SAXException {
        Log log = new Log();
        log.setBody(bodyString);
        log.setStatus("SUCCES");
        Element rootElement = getRootElement(bodyString);
        log.setOperationName(rootElement.getName());
        log.setRqUid(rootElement.getChild("RqUID").getValue());
        log.setRqTime(rootElement.getChild("RqTm").getValue());
        log.setPutTime();
        log.setAdapter(savedClass.getSimpleName());
        return log;
    }

    private Log prepareLogEntity(PrivateOperStateRq privateOperStateRq, Class savedClass) throws ParserConfigurationException, SAXException {
        Log log = new Log();
        log.setBody(privateOperStateRq.toString());
        log.setStatus("SUCCES");
        log.setOperationName(privateOperStateRq.getClass().getSimpleName());
        log.setRqUid(privateOperStateRq.getRqUID());
        log.setRqTime(privateOperStateRq.getRqTm().toString());
        log.setPutTime();
        log.setAdapter(savedClass.getSimpleName());
        return log;
    }
}
