package cn.windylee.dubbo;

import cn.windylee.dubbo.impl.GreetingServiceImpl;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import javafx.application.Application;
import org.junit.Before;
import org.junit.Test;

import java.lang.ref.Reference;

public class GreetingServiceUseJavaTest {

    @Before
    public void initRemote(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("demo-provider");
        applicationConfig.setVersion("1.0");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("multicast://224.1.1.1:9090");

        ServiceConfig<GreetingService> serviceConfig = new ServiceConfig<GreetingService>();
        serviceConfig.setInterface(GreetingService.class);
        serviceConfig.setRef(new GreetingServiceImpl());
        serviceConfig.setApplication(applicationConfig);
        serviceConfig.setRegistry(registryConfig);

        serviceConfig.export();

    }

    @Test
    public void sayHiTest(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("demo-consumer");
        applicationConfig.setVersion("1.0");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("multicast://224.1.1.1:9090");

        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<GreetingService>();
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);

        GreetingService greetingService = referenceConfig.get();
        greetingService.sayHi("windylee");
    }

}
