package cn.windylee.dubbo;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.misc.resources.Messages_sv;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GreetingServiceUseXmlTest {

//    @Before
    public void initRemote(){
        ClassPathXmlApplicationContext remoteContext = new ClassPathXmlApplicationContext("classpath:provider-app.xml");
        remoteContext.start();
    }

    @Test
    public void testSayHi(){
        initRemote();
        ClassPathXmlApplicationContext clientContext = new ClassPathXmlApplicationContext("classpath:consumer-app.xml");
        clientContext.start();
        GreetingService greetingService = (GreetingService) clientContext.getBean("greetingService");
        System.out.println(greetingService.sayHi("windylee"));
    }

    @Test
    public void testCache(){
        ClassPathXmlApplicationContext remoteContext = new ClassPathXmlApplicationContext("classpath:provider-app-special.xml");
        remoteContext.start();

        ClassPathXmlApplicationContext clientContext = new ClassPathXmlApplicationContext("classpath:consumer-app.xml");
        clientContext.start();

        GreetingService greetingService = (GreetingService) clientContext.getBean("greetingService");
        long before = System.currentTimeMillis();
        String message = greetingService.sayHi("windylee");
        long timeElapsed = System.currentTimeMillis() - before;
        Assert.assertTrue(timeElapsed>5000);
        Assert.assertNotNull(message);
        Assert.assertEquals("hi, windylee", message);

        before = System.currentTimeMillis();
        message = greetingService.sayHi("windylee");
        timeElapsed = System.currentTimeMillis() - before;
        Assert.assertTrue(timeElapsed < 5000);
        Assert.assertNotNull(message);
        Assert.assertEquals("hi, windylee", message);

    }

//    使用集群部署时，可自动进行负载均衡
    @Test
    public void testLoadBalance(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider-app.xml");
            context.start();
        });
        executorService.submit(()->{
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider-app-special.xml");
            context.start();
        });

        ClassPathXmlApplicationContext localContext
                = new ClassPathXmlApplicationContext("classpath:consumer-app.xml");
        localContext.start();
        GreetingService greetingsService
                = (GreetingService) localContext.getBean("greetingService");

        List<Long> elapseList = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            long current = System.currentTimeMillis();
            String hiMessage = greetingsService.sayHi("windylee");
            Assert.assertNotNull(hiMessage);
            elapseList.add(System.currentTimeMillis() - current);
        }

        OptionalDouble avgElapse = elapseList
                .stream()
                .mapToLong(e -> e)
                .average();
        Assert.assertTrue(avgElapse.isPresent());
        Assert.assertTrue(avgElapse.getAsDouble() > 2500.0);
    }


}
