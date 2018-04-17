package cn.windylee.dubbo.impl;

import cn.windylee.dubbo.GreetingService;

public class GreetingsServiceSpecialImpl implements GreetingService{
    public String sayHi(String name) {
        try {
            System.out.println("specially called");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hi, " + name;
    }
}
