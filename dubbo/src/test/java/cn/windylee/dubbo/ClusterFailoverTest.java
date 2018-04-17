package cn.windylee.dubbo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClusterFailoverTest {

    private ExecutorService executorService;

    @Before
    public void initRemote(){
        executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
            ClassPathXmlApplicationContext backupRemoteContext = new ClassPathXmlApplicationContext("cluster/provider-app-special.xml");
            backupRemoteContext.start();
        });
        executorService.submit(()->{
            ClassPathXmlApplicationContext remoteContext = new ClassPathXmlApplicationContext("cluster/provider-app-failover.xml");
            remoteContext.start();
        });
    }

    @Test
    public void testSayHi(){
        ClassPathXmlApplicationContext localContext = new ClassPathXmlApplicationContext("cluster/consumer-app-failtest.xml");
        localContext.start();

        GreetingService greetingService = (GreetingService) localContext.getBean("greetingService");
        String hiMessage = greetingService.sayHi("windylee");
        Assert.assertNotNull(hiMessage);
        Assert.assertEquals("hi, failover windylee", hiMessage);
    }

    @After
    public void destroy(){
        executorService.shutdown();
    }
}
