package cn.windylee.dubbo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleRegistryTest {

    @Before
    public void initRemote(){
        ClassPathXmlApplicationContext registryContext = new ClassPathXmlApplicationContext("classpath:simple/registry.xml");
        registryContext.start();

        ClassPathXmlApplicationContext remoteContext = new ClassPathXmlApplicationContext("classpath:simple/provider-app.xml");
        remoteContext.start();
    }

    @Test
    public void testSayHi(){
        ClassPathXmlApplicationContext localContext = new ClassPathXmlApplicationContext("classpath:simple/consumer-app.xml");
        localContext.start();

        GreetingService greetingService = (GreetingService) localContext.getBean("greetingService");
        System.out.println(greetingService.sayHi("windylee"));
    }

}
