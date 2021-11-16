package org.example.safe.model.dto;

import org.example.safe.model.Item;

import java.util.List;

public class SafeDataDto {
    private int safeCapacity;
    private List<Item> items;

    public SafeDataDto(int safeCapacity, List<Item> items) {
        this.safeCapacity = safeCapacity;
        this.items = items;
    }

    public int getSafeCapacity() {
        return safeCapacity;
    }

    public List<Item> getItems() {
        return items;
    }
}
