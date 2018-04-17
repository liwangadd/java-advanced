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
import java.util.concurrent.TimeUnit;

public class ClusterDynamicLoadBalanceTest {

    private ExecutorService executorService;

    @Before
    public void initRemote() {
        executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            ClassPathXmlApplicationContext remoteContext = new ClassPathXmlApplicationContext("cluster/provider-app-default.xml");
            remoteContext.start();
        });
        executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(2);
            ClassPathXmlApplicationContext backupRemoteContext = new ClassPathXmlApplicationContext("cluster/provider-app-special.xml");
            backupRemoteContext.start();
            return null;
        });
    }

    @Test
    public void testSayHi() throws InterruptedException {
        ClassPathXmlApplicationContext localContext = new ClassPathXmlApplicationContext("cluster/consumer-app-lb.xml");
        localContext.start();

        GreetingService greetingService = (GreetingService) localContext.getBean("greetingService");
        List<Long> elapseList = new ArrayList<>();
        for(int i=0;i<6;i++){
            long before = System.currentTimeMillis();
            String hiMessage = greetingService.sayHi("windylee");
            Assert.assertNotNull(hiMessage);
            elapseList.add(System.currentTimeMillis() - before);
            TimeUnit.SECONDS.sleep(1);
        }

        OptionalDouble avgElapse = elapseList.stream()
                .mapToDouble(e -> e)
                .average();
        Assert.assertTrue(avgElapse.isPresent());
        Assert.assertTrue(avgElapse.getAsDouble() > 1666.0);
    }

    @After
    public void destroy(){
        executorService.shutdown();
    }
}
