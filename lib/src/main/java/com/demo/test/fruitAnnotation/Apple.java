package com.demo.test.fruitAnnotation;

/**
 * @author :  lwb
 * Date: 2019/10/8
 * Desc: 注解使用
 */
public class Apple {

    @FruitName("Apple")
    private String name;
    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String address;
    @FruitProducerFactory(id = 1,name = "小苹果",address = "北京市")
    private int producer;

    public Apple() {
    }

    public Apple(String name, String address, int producer) {
        this.name = name;
        this.address = address;
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getProducer() {
        return producer;
    }

    public void setProducer(int producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", producer=" + producer +
                '}';
    }
}


