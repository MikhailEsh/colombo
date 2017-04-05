package main.logger.service;

import main.logger.config.DataConfigTest;
import main.schemas.srvprivateoperstate.PrivateOperStateRq;
import main.schemas.srvprivateoperstate.PrivateOperStateRs;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import main.utils.TestUtils;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Created by sbt-eshtokin-ml on 30.03.2017.
 */

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfigTest.class)
public class LogServiceImplTest {

    /*@Resource
    private EntityManagerFactory emf;
    protected EntityManager em;

    @Resource
    LogService logService;


    private TestUtils testUtils = new TestUtils();
    private String testTextMessage;
    private PrivateOperStateRq testPrivateOperStateRq;
    private PrivateOperStateRs testPrivateOperStateRs;

    @Before
    @Transactional
    public void setUp() throws Exception {
        testTextMessage = testUtils.getTextMessage();
        testPrivateOperStateRq = testUtils.getTestPrivateOperStateRq();
        testPrivateOperStateRs = testUtils.getTestPrivateOperStateRs();
        em = emf.createEntityManager();
    }

    @Test
    public void testSaveBankByString() throws Exception {
        logService.saveLog(testTextMessage, this.getClass());
    }

    @Test
    public void testSaveBankByObjectRq() throws Exception {
        logService.saveLog(testPrivateOperStateRq, this.getClass());
    }

    @Test
    public void testSaveBankByObjectRs() throws Exception {
        logService.saveLog(testPrivateOperStateRs, this.getClass());
    }*/
    @Test
    public void testSaveBankByObjectRs() throws Exception {

    }

}


