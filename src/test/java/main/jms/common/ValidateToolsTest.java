package main.jms.common;

import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.Test;
import main.utils.TestUtils;

import java.net.URL;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */
public class ValidateToolsTest {

    public TestUtils testUtils = new TestUtils();

    private byte[] xml ;

    private URL xsdURL;



    @Before
    public void init()
    {
        xml = testUtils.getTextMessage().getBytes();
        xsdURL = Resources.getResource(this.getClass(),
                (testUtils.getTestXSD()));
    }

    @Test
    public void call()
    {
        ValidateTools validateTools = new ValidateToolsImpl();
        validateTools.validateByURL(xsdURL, xml);
    }
}
