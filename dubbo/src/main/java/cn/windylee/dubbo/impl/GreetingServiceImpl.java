package cn.windylee.dubbo.impl;

import cn.windylee.dubbo.GreetingService;

public class GreetingServiceImpl implements GreetingService {

    public String sayHi(String name) {
        return "hi, " + name;
    }
}
