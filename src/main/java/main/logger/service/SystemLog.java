package main.logger.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by sbt-eshtokin-ml on 04.04.2017.
 */
public class SystemLog {
    public static final Logger log = LogManager.getLogger(SystemLog.class);
    static public void SaveErrorLog(Class savedClass, Object error)
    {
        log.error(savedClass.getName(), error);
    }
}
