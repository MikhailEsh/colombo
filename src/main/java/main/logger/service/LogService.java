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
    public void saveLog(String bodyString, Class savedClass) ;
    public void saveLog(PrivateOperStateRq privateOperStateRq, Class savedClass);
    public void saveLog(PrivateOperStateRs privateOperStateRs, Class savedClass);
}
