package org.example.safe.model;

import org.example.safe.model.Item;

import java.util.List;

public class Safe {
    private final int capacity;
    private List<Item> items;

    public Safe(int capacity) {
        this.capacity = capacity;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Safe{" +
                "capacity=" + capacity +
                ", items=" + items +
                '}';
    }
}
