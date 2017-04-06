package main.jms.receiver;

import main.jms.common.MarshallingObject;
import main.jms.common.MessageWrapper;
import main.jms.common.stubs.MarshallingObjectImplStub;
import main.jms.common.stubs.MessageWrapperImplStub;
import main.jms.connect.RemoteConnection;
import main.jms.connect.stubs.RemoteConnectionImplStub;
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
    private RemoteConnection remoteConnection;
    private MessageWrapper messageWrapper;

    @Before
    public void init()
    {
        this.logService = new ServiceLogImplStub();
        this.marshallingObject = new MarshallingObjectImplStub();
        this.remoteConnection = new RemoteConnectionImplStub();
        this.messageWrapper = new MessageWrapperImplStub();
    }


    @Test
    public void execMessage() throws Exception {
        MessageProcessor messageProcessor = new MessageProcessorSingleImpl(logService, marshallingObject, remoteConnection);
        messageProcessor.execMessage(messageWrapper);
    }

}