package cn.windylee.dubbo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class ResultCacheTest {

    private ClassPathXmlApplicationContext remoteContext;

    @Before
    public void initRemote() {
        remoteContext = new ClassPathXmlApplicationContext("multicast/provider-app-special.xml");
        remoteContext.start();
    }

    @Test
    public void givenProvider_whenConsumerSaysHi_thenGotResponse() {
        ClassPathXmlApplicationContext localContext = new ClassPathXmlApplicationContext("multicast/consumer-app.xml");
        localContext.start();
        GreetingService greetingsService = (GreetingService) localContext.getBean("greetingService");

        long before = System.currentTimeMillis();
        String hiMessage = greetingsService.sayHi("windylee");

        long timeElapsed = System.currentTimeMillis() - before;
        assertTrue(timeElapsed > 5000);
        assertNotNull(hiMessage);
        assertEquals("hi, windylee", hiMessage);

        before = System.currentTimeMillis();
        hiMessage = greetingsService.sayHi("windylee");
        timeElapsed = System.currentTimeMillis() - before;
        assertTrue(timeElapsed < 1000);
        assertNotNull(hiMessage);
        assertEquals("hi, windylee", hiMessage);
    }

}
