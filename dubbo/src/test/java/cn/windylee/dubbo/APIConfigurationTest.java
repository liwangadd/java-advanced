package cn.windylee.dubbo;

import cn.windylee.dubbo.impl.GreetingServiceImpl;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import org.junit.Before;
import org.junit.Test;

public class APIConfigurationTest {

    @Before
    public void initRemote(){
        ApplicationConfig application = new ApplicationConfig();
        application.setName("demo-provider");
        application.setVersion("1.0");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("multicast://224.1.1.1:9090");

        ServiceConfig<GreetingService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(application);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setInterface(GreetingService.class);
        serviceConfig.setRef(new GreetingServiceImpl());

        serviceConfig.export();
    }

    @Test
    public void testSayHi(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("demo-consumer");
        applicationConfig.setVersion("1.0");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("multicast://224.1.1.1:9090");

        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(GreetingService.class);

        GreetingService greetingService = referenceConfig.get();
        System.out.println(greetingService.sayHi("windylee"));
    }

}
