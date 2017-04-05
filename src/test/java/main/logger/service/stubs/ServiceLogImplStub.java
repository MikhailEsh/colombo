package main.logger.service.stubs;

import main.logger.entity.Log;
import main.logger.service.LogService;
import main.schemas.srvprivateoperstate.PrivateOperStateRq;
import main.schemas.srvprivateoperstate.PrivateOperStateRs;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */
public class ServiceLogImplStub implements LogService{

    public Log saveLog(String bodyString, Class c) throws ParserConfigurationException, SAXException
    {
        return null;
    }

    public Log saveLog(PrivateOperStateRq privateOperStateRq, Class c) {
        return null;
    }

    public Log saveLog(PrivateOperStateRs privateOperStateRs, Class c) throws ParserConfigurationException, SAXException {
        return null;
    }
}
