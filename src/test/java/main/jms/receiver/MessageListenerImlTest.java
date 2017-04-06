package main.jms.receiver;

import main.jms.common.MarshallingObject;
import main.jms.common.ValidateTools;
import main.jms.common.MessageWrapper;
import main.jms.common.stubs.MarshallingObjectImplStub;
import main.jms.common.stubs.MessageWrapperImplStub;
import main.jms.common.stubs.ValidateToolsImplStubImpl;
import main.logger.service.LogService;
import main.logger.service.stubs.ServiceLogImplStub;
import main.utils.TestDatas;
import org.junit.Before;
import org.junit.Test;

import javax.jms.JMSException;
import javax.jms.MessageListener;
import java.io.IOException;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */
public class MessageListenerImlTest{

    private MessageListener messageListener;
    private TestDatas testDatas = new TestDatas();

    @Before
    public void init()
    {
        ValidateTools validateTools = new ValidateToolsImplStubImpl();
        MarshallingObject marshallingObject = new MarshallingObjectImplStub();
        MessageWrapper messageWrapper = new MessageWrapperImplStub();
        LogService serviceLog = new ServiceLogImplStub();
        messageListener = new MessageListenerIml(validateTools, serviceLog , messageWrapper);

    }

    @Test
    public void call() throws InterruptedException, JMSException, IOException {
        //Проблема с подготовкой тестового Message

    }
}
