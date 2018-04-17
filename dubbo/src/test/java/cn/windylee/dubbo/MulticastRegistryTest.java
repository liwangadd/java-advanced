package cn.windylee.dubbo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MulticastRegistryTest {

    @Before
    public void initRemote(){
        ClassPathXmlApplicationContext remoteContext = new ClassPathXmlApplicationContext("classpath:multicast/provider-app.xml");
        remoteContext.start();
    }

    @Test
    public void testSayHi(){
        ClassPathXmlApplicationContext localContext = new ClassPathXmlApplicationContext("classpath:multicast/consumer-app.xml");
        localContext.start();

        GreetingService greetingService = (GreetingService) localContext.getBean("greetingService");
        System.out.println(greetingService.sayHi("windylee"));
    }

}
