package org.example.safe.model;

import java.util.ArrayList;
import java.util.List;

public class Safe {
    private final int capacity;
    private List<Item> items = new ArrayList<>();

    public Safe(int capacity) {
        this.capacity = capacity;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
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
