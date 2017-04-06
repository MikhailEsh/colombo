package main.jms;

import main.jms.connect.RemoteConnectionReader;
import main.jms.connect.RemoteConnectionSender;
import main.jms.receiver.MessageListenerIml;
import main.logger.service.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

/**
 * Created by sbt-eshtokin-ml on 24.03.2017.
 */
@Component("jMSRun")
public class JMSRun implements Runnable {


    @Autowired
    private RemoteConnectionReader remoteConnectionReader;

    @Autowired
    private RemoteConnectionSender remoteConnectionSender;

    @Autowired
    MessageListenerIml messageListener;

    public void run()
    {
        try {
            remoteConnectionSender.connect();
            remoteConnectionReader.connect();
            remoteConnectionReader.runConnectListner(messageListener);

            for (;;) {
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        } catch (JMSException e) {
            SystemLog.SaveErrorLog(this.getClass(), e);
        }
    }
}
