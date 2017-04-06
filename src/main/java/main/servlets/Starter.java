package main.servlets;

import main.jms.JMSRun;
import main.logger.entity.Log;
import main.logger.service.LogService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServlet;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by sbt-eshtokin-ml on 24.03.2017.
 */
public class Starter extends HttpServlet {



    @Override
    public void init()
    {
        System.out.println("---------------------------------------------------------------------------------------------");
        Date date = new Date();
        long time = date.getTime();
        Timestamp putTime = new Timestamp(time);
        System.out.println(putTime);
        System.out.println("main.Starter started Misha");
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context.xml");
        JMSRun jmsRun = (JMSRun)context.getBean("jMSRun");
        Thread threadReaderJMS = new Thread(jmsRun);
        threadReaderJMS.start();
        System.out.println("init ended Misha");
    }

    //Для отладки
    public static void main(String[] s) {
        System.out.println("HttpServlet - run - MISHA1");
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:context_logger.xml");
        LogService logService = (LogService)context.getBean("logServiceSchedulerImpl");
        System.out.println("HttpServlet - run - MISkHA2");
    }
}
