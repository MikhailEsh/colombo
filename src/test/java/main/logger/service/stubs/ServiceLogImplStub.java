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

    public void saveLog(String bodyString, Class savedClass) {

    }

    public void saveLog(PrivateOperStateRq privateOperStateRq, Class savedClass) {

    }

    public void saveLog(PrivateOperStateRs privateOperStateRs, Class savedClass) {

    }
}
