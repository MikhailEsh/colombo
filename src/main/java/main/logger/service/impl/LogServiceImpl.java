package main.logger.service.impl;

import main.logger.entity.Log;
import main.logger.repository.LogEntityRepository;
import main.logger.service.LogService;
import main.logger.service.SystemLog;
import main.schemas.srvprivateoperstate.PrivateOperStateRq;
import main.schemas.srvprivateoperstate.PrivateOperStateRs;
import main.schemas.srvprivateoperstate.StatusType;
import main.schemas.srvprivateoperstate.TransferLineInfoType;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */

@Service("logService")
@Repository
public class LogServiceImpl implements LogService {


    @Autowired
    private LogEntityRepository logEntityRepository;

    public Log saveLog(String bodyString, Class savedClass) throws ParserConfigurationException, SAXException {
        Log log = prepareLogEntity(bodyString, savedClass);
        Log savedLog = logEntityRepository.saveAndFlush(log);
        return savedLog;
    }

    public Log saveLog(PrivateOperStateRq privateOperStateRq, Class savedClass) throws ParserConfigurationException, SAXException {
        Log log = prepareLogEntity(privateOperStateRq, savedClass);
        Log savedLog = logEntityRepository.saveAndFlush(log);
        return savedLog;
    }

    public Log saveLog(PrivateOperStateRs privateOperStateRs, Class savedClass) throws ParserConfigurationException, SAXException {
        Log log = prepareLogEntity(privateOperStateRs, savedClass);
        Log savedLog = logEntityRepository.saveAndFlush(log);
        return savedLog;
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
