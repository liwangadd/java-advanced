package cn.windylee.stream;

import java.util.List;
import java.util.stream.Stream;

public class Product {

    private int price;

    private String name;

    private boolean utilize;

    public Product(int price, String name) {
        this(price);
        this.name = name;
    }

    public Product(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUtilize() {
        return utilize;
    }

    public void setUtilize(boolean utilize) {
        this.utilize = utilize;
    }

    public static Stream<String> streamOf(List<String> list) {
        return (list == null || list.isEmpty()) ? Stream.empty() : list.stream();
    }
}
