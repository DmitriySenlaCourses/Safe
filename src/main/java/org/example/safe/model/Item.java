package org.example.safe.model;

public class Item {
    private final String name;
    private final int volume;
    private final int price;

    public Item(String name, int volume, int price) {
        this.name = name;
        this.volume = volume;
        this.price = price;
    }

    public int getVolume() {
        return volume;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", weight=" + volume +
                ", price=" + price +
                '}';
    }
}
