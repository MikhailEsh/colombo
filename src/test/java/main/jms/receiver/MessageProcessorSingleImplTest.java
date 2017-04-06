package main.jms.receiver;

import main.jms.common.MarshallingObject;
import main.jms.common.MessageWrapper;
import main.jms.common.stubs.MarshallingObjectImplStub;
import main.jms.common.stubs.MessageWrapperImplStub;
import main.jms.connect.RemoteConnectionSender;
import main.jms.connect.stubs.RemoteConnectionSenderImplStub;
import main.logger.service.LogService;
import main.logger.service.stubs.ServiceLogImplStub;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by sbt-eshtokin-ml on 31.03.2017.
 */
public class MessageProcessorSingleImplTest {

    private LogService logService;
    private MarshallingObject marshallingObject;
    private RemoteConnectionSender remoteConnectionSender;
    private MessageWrapper messageWrapper;

    @Before
    public void init()
    {
        this.logService = new ServiceLogImplStub();
        this.marshallingObject = new MarshallingObjectImplStub();
        this.remoteConnectionSender = new RemoteConnectionSenderImplStub();
        this.messageWrapper = new MessageWrapperImplStub();
    }


    @Test
    public void execMessage() throws Exception {
        MessageProcessor messageProcessor = new MessageProcessorSingleImpl(logService, marshallingObject, remoteConnectionSender);
        messageProcessor.execMessage(messageWrapper);
    }

}