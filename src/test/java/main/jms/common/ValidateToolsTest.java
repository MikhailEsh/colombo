package main.jms.common;

import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.Test;
import main.utils.TestDatas;

import java.net.URL;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */
public class ValidateToolsTest {

    public TestDatas testDatas = new TestDatas();
    private byte[] testXml;
    private URL testXSD;



    @Before
    public void init()
    {
        testXml = testDatas.getTestText().getBytes();
        testXSD = Resources.getResource(this.getClass(),
                (testDatas.getTestXSD()));
    }

    @Test
    public void call()
    {
        ValidateTools validateTools = new ValidateToolsImpl();
        validateTools.validateByURL(testXSD, testXml);
    }
}
