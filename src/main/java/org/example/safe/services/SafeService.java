package org.example.safe.services;

import org.example.safe.model.Item;
import org.example.safe.model.Safe;

import java.util.ArrayList;
import java.util.List;

public class SafeService {

    public Safe fillSafe(Safe safe, List<Item> itemList) {
        int safeCapacity = safe.getCapacity();
        Safe[] maxPricesEachSafeVolumePreviousItem = new Safe[safeCapacity + 1];
        setEmptySafeForEmptyListItems(maxPricesEachSafeVolumePreviousItem);

        for (int itemNumber = 0; itemNumber < itemList.size(); itemNumber++) {
            Safe[] maxPricesEachSafeVolumeCurrentItem = new Safe[safeCapacity + 1];
            for (int safeVolumeStep = 0; safeVolumeStep < maxPricesEachSafeVolumeCurrentItem.length; safeVolumeStep++) {
                Item item = itemList.get(itemNumber);
                int itemVolume = item.getVolume();
                int itemPrice = item.getPrice();

                if (safeVolumeStep >= itemVolume) {
                    if (getSafeItemPriceSum(maxPricesEachSafeVolumePreviousItem[safeVolumeStep]) >
                            getSafeItemPriceSum(maxPricesEachSafeVolumePreviousItem[safeVolumeStep - itemVolume]) + itemPrice) {

                        maxPricesEachSafeVolumeCurrentItem[safeVolumeStep] =
                                maxPricesEachSafeVolumePreviousItem[safeVolumeStep];
                    } else {
                        Safe newSafe = copySafeAndAddItem(maxPricesEachSafeVolumePreviousItem[safeVolumeStep - itemVolume],
                                item, safeVolumeStep);
                        maxPricesEachSafeVolumeCurrentItem[safeVolumeStep] = newSafe;
                    }
                } else {
                    maxPricesEachSafeVolumeCurrentItem[safeVolumeStep] =
                            maxPricesEachSafeVolumePreviousItem[safeVolumeStep];
                }
            }
            maxPricesEachSafeVolumePreviousItem = maxPricesEachSafeVolumeCurrentItem;
        }
        return maxPricesEachSafeVolumePreviousItem[maxPricesEachSafeVolumePreviousItem.length - 1];
    }

    private int getSafeItemPriceSum(Safe safe) {
        return safe.getItems().stream().mapToInt(Item::getPrice).sum();
    }

    private void setEmptySafeForEmptyListItems(Safe[] safe) {
        for (int i = 0; i < safe.length; i++) {
            safe[i] = new Safe(i);
        }
    }

    private Safe copySafeAndAddItem(Safe safe, Item item, int newSafeCapacity) {
        List<Item> tempItems = safe.getItems();
        Safe newSafe = new Safe(newSafeCapacity);
        List<Item> newSafeItems = new ArrayList<>(tempItems);
        newSafeItems.add(item);
        newSafe.setItems(newSafeItems);
        return newSafe;
    }
}
