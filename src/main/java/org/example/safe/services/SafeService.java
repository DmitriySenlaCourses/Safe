package org.example.safe.services;

import org.example.safe.model.Item;
import org.example.safe.model.Safe;

import java.util.ArrayList;
import java.util.List;

public class SafeService {

    public static List<Item> fillSafe(Safe safe, List<Item> itemList) {
        int safeCapacity = safe.getCapacity();
        int[][] maxPrices = new int[itemList.size() + 1][safeCapacity + 1];

        for (int itemNumber = 1; itemNumber <= itemList.size(); itemNumber++) {
            for (int volume = 1; volume <= safeCapacity; volume++) {
                int itemVolume = itemList.get(itemNumber -1).getVolume();
                int itemPrice = itemList.get(itemNumber -1).getPrice();

                if (volume >= itemVolume) {
                    maxPrices[itemNumber][volume] = Math.max(maxPrices[itemNumber - 1][volume], maxPrices[itemNumber - 1][volume - itemVolume] + itemPrice);
                } else {
                    maxPrices[itemNumber][volume] = maxPrices[itemNumber - 1][volume];
                }
            }
        }
        return getItems(maxPrices, itemList);
    }

    private static List<Item> getItems(int[][] maxPrices, List<Item> itemList) {
        List<Item> items = new ArrayList<>();
        int itemNumber = maxPrices.length -1;
        int volume = maxPrices[0].length - 1;
        int maxPrice = maxPrices[itemNumber][volume];
        while (maxPrice > 0) {
            if (maxPrice != maxPrices[itemNumber - 1][volume]) {
                maxPrice = maxPrice - itemList.get(itemNumber - 1).getPrice();
                items.add(itemList.get(itemNumber - 1));
                volume = volume - itemList.get(itemNumber - 1).getVolume();
                --itemNumber;
            }else {
                --itemNumber;
            }
        }
        return items;
    }
}
