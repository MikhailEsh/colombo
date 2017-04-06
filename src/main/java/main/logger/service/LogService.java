package main.logger.service;

import main.logger.entity.Log;
import main.schemas.srvprivateoperstate.PrivateOperStateRq;
import main.schemas.srvprivateoperstate.PrivateOperStateRs;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */
public interface LogService {
    public Log saveLog(String bodyString, Class savedClass) throws ParserConfigurationException, SAXException;
    public Log saveLog(PrivateOperStateRq privateOperStateRq, Class savedClass) throws ParserConfigurationException, SAXException;
    public Log saveLog(PrivateOperStateRs privateOperStateRs, Class savedClass) throws ParserConfigurationException, SAXException;
}
