package main;

import main.logger.service.SystemLog;

/**
 * Created by sbt-eshtokin-ml on 03.04.2017.
 */
public class Utils {

    public static void TimeWaite()
    {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            SystemLog.SaveErrorLog(Utils.class, e);
        }
    }
}
