package org.example.safe.services;

import org.example.safe.model.Item;
import org.example.safe.model.Safe;

import java.util.ArrayList;
import java.util.List;

public class SafeService {

    public Safe fillSafe(Safe safe, List<Item> itemList) {
        int safeCapacity = safe.getCapacity();
        Safe[][] maxPricesEachSafeVolumeAndItemSet = new Safe[itemList.size() + 1][safeCapacity + 1];

        setEmptySafeForEmptyListItemAndZeroCapacityStepSafe(maxPricesEachSafeVolumeAndItemSet);

        for (int itemNumber = 1; itemNumber <= itemList.size(); itemNumber++) {
            for (int safeVolumeStep = 1; safeVolumeStep <= safeCapacity; safeVolumeStep++) {
                Item item = itemList.get(itemNumber - 1);
                int itemVolume = item.getVolume();
                int itemPrice = item.getPrice();

                if (safeVolumeStep >= itemVolume) {
                    if (getSafeItemPriceSum(maxPricesEachSafeVolumeAndItemSet[itemNumber - 1][safeVolumeStep]) >
                            getSafeItemPriceSum(maxPricesEachSafeVolumeAndItemSet[itemNumber - 1][safeVolumeStep - itemVolume]) + itemPrice) {

                        maxPricesEachSafeVolumeAndItemSet[itemNumber][safeVolumeStep] =
                                maxPricesEachSafeVolumeAndItemSet[itemNumber - 1][safeVolumeStep];
                    } else {
                        Safe newSafe = copySafeAndAddItem(maxPricesEachSafeVolumeAndItemSet[itemNumber - 1][safeVolumeStep - itemVolume],
                                item,
                                safeVolumeStep);

                        maxPricesEachSafeVolumeAndItemSet[itemNumber][safeVolumeStep] = newSafe;
                    }
                } else {
                    maxPricesEachSafeVolumeAndItemSet[itemNumber][safeVolumeStep] =
                            maxPricesEachSafeVolumeAndItemSet[itemNumber - 1][safeVolumeStep];
                }
            }
        }
        int itemNumber = maxPricesEachSafeVolumeAndItemSet.length - 1;
        int volume = maxPricesEachSafeVolumeAndItemSet[0].length - 1;
        return maxPricesEachSafeVolumeAndItemSet[itemNumber][volume];
    }

    private int getSafeItemPriceSum(Safe safe) {
        return safe.getItems().stream().mapToInt(Item::getPrice).sum();
    }

    private void setEmptySafeForEmptyListItemAndZeroCapacityStepSafe(Safe[][] maxPricesEachSafeVolumeAndItemSet) {
        for (int itemNumber = 0; itemNumber < maxPricesEachSafeVolumeAndItemSet.length; itemNumber++) {
            maxPricesEachSafeVolumeAndItemSet[itemNumber][0] = new Safe(0);
        }

        for (int safeVolumeStep = 0; safeVolumeStep < maxPricesEachSafeVolumeAndItemSet[0].length; safeVolumeStep++) {
            maxPricesEachSafeVolumeAndItemSet[0][safeVolumeStep] = new Safe(safeVolumeStep);
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
