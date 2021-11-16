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
            for (int safeVolume = 0; safeVolume < maxPricesEachSafeVolumeCurrentItem.length; safeVolume++) {
                Item item = itemList.get(itemNumber);
                maxPricesEachSafeVolumeCurrentItem[safeVolume] =
                        optimalFillingForCurrentVolumeAndItem(safeVolume, item, maxPricesEachSafeVolumePreviousItem);
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

    private Safe copySafeContent(Safe from, Safe to) {
        List<Item> tempItems = from.getItems();
        List<Item> newSafeItems = new ArrayList<>(tempItems);
        to.setItems(newSafeItems);
        return to;
    }

    private Safe addItemToSafe(Safe to, Item item) {
        List<Item> tempItems = to.getItems();
        tempItems.add(item);
        return to;
    }

    private Safe optimalFillingForCurrentVolumeAndItem(int safeVolume, Item item, Safe[] maxPricesEachSafeVolumePreviousItem) {
        int itemVolume = item.getVolume();
        Safe safeWithoutCurrentItem = copySafeContent(maxPricesEachSafeVolumePreviousItem[safeVolume], new Safe(safeVolume));

        if (safeVolume >= itemVolume) {
            Safe tempSafe = copySafeContent(maxPricesEachSafeVolumePreviousItem[safeVolume - itemVolume], new Safe(safeVolume));
            Safe safeWithCurrentItem = addItemToSafe(tempSafe, item);

            if (getSafeItemPriceSum(safeWithCurrentItem) > getSafeItemPriceSum(safeWithoutCurrentItem)) {
                return safeWithCurrentItem;
            } else {
                return safeWithoutCurrentItem;
            }
        } else {
            return safeWithoutCurrentItem;
        }
    }
}
