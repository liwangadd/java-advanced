package cn.windylee.defaulttaticinterfacemethods.model;

public class Motorbike implements Vehicle{

    private String brand;

    public Motorbike(String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String speedUp() {
        return "the motorbike is speeding up";
    }

    @Override
    public String slowDown() {
        return "the motorbike is slowing down";
    }
}
