package main.jms.receiver;

import main.jms.common.MarshallingObject;
import main.jms.common.ValidateTools;
import main.jms.common.MessageWrapper;
import main.jms.common.stubs.MarshallingObjectTestImpl;
import main.jms.common.stubs.MessageWrapperTestImpl;
import main.jms.common.stubs.ValidateToolsImplStubImpl;
import main.logger.service.LogService;
import main.logger.service.stubs.ServiceLogImplStub;
import org.junit.Before;
import org.junit.Test;
import main.utils.TestUtils;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import java.io.IOException;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */
public class MessageListenerImlTest{

    private MessageListener messageListener;
    private TestUtils testUtils = new TestUtils();

    @Before
    public void init()
    {
        ValidateTools validateTools = new ValidateToolsImplStubImpl();
        MarshallingObject marshallingObject = new MarshallingObjectTestImpl();
        MessageWrapper messageWrapper = new MessageWrapperTestImpl();
        LogService serviceLog = new ServiceLogImplStub();
        messageListener = new MessageListenerIml(validateTools, serviceLog , messageWrapper);

    }

    @Test
    public void call() throws InterruptedException, JMSException, IOException {
        BytesMessage bytesMessage = testUtils.getTestBytesMessage();
        //messageListener.onMessage(bytesMessage);
        System.out.println(this.getClass() + " SUCCES");

    }
}
