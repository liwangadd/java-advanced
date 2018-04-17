package cn.windylee.dubbo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClusterLoadBalanceTest {

    private ExecutorService executorService;

    @Before
    public void initRemote(){
        executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()->{
            ClassPathXmlApplicationContext remoteContext = new ClassPathXmlApplicationContext("cluster/provider-app-default.xml");
            remoteContext.start();
        });
        executorService.submit(()->{
            ClassPathXmlApplicationContext backupRemoteContext = new ClassPathXmlApplicationContext("cluster/provider-app-special.xml");
            backupRemoteContext.start();
        });
    }

    @Test
    public void name() {
    }

    @Test
    public void sayHi(){
        ClassPathXmlApplicationContext localContext = new ClassPathXmlApplicationContext("cluster/consumer-app-lb.xml");
        localContext.start();

        GreetingService greetingService = (GreetingService) localContext.getBean("greetingService");
        List<Long> elapseList = new ArrayList<>();

        for(int i=0;i<6;i++){
            long current = System.currentTimeMillis();
            String hiMessage = greetingService.sayHi("windylee");
            Assert.assertNotNull(hiMessage);
            elapseList.add(System.currentTimeMillis() - current);
        }

        OptionalDouble avgElapse = elapseList
                .stream()
                .mapToDouble(e->e)
                .average();

        Assert.assertTrue((avgElapse.isPresent()));
        System.out.println(avgElapse.getAsDouble());
        Assert.assertTrue(avgElapse.getAsDouble()>2500);
    }

    @After
    public void destroy(){
        executorService.shutdown();
    }

}
