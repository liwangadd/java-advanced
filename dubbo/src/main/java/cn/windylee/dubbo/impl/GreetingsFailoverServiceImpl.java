package cn.windylee.dubbo.impl;

import cn.windylee.dubbo.GreetingService;

public class GreetingsFailoverServiceImpl implements GreetingService {
    public String sayHi(String name) {
        System.out.println("failover implementation");
        return "hi, failover " + name;
    }
}
