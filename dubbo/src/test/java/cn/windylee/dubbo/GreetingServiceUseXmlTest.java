package cn.windylee.dubbo;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GreetingServiceUseXmlTest {

    @Before
    public void initRemote(){
        ClassPathXmlApplicationContext remoteContext = new ClassPathXmlApplicationContext("classpath:provider-app.xml");
        remoteContext.start();
    }

    @Test
    public void sayHiTest(){
        ClassPathXmlApplicationContext clientContext = new ClassPathXmlApplicationContext("classpath:consumer-app.xml");
        clientContext.start();
        GreetingService greetingService = (GreetingService) clientContext.getBean("greetingService");
        System.out.println(greetingService.sayHi("windylee"));
    }

}
