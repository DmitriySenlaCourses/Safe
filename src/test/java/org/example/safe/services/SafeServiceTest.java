package org.example.safe.services;

import org.example.safe.model.Item;
import org.example.safe.model.Safe;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SafeServiceTest {

    @Test
    public void fillSafe() {
        Safe safe = new Safe(13);
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("item1",3,1));
        itemList.add(new Item("item2",4,6));
        itemList.add(new Item("item3",5,4));
        itemList.add(new Item("item4",8,7));
        itemList.add(new Item("item5",9,6));

        SafeService safeService = new SafeService();
        Safe fillingSafe = safeService.fillSafe(safe, itemList);

        int actualSafePrice = fillingSafe.getItems().stream().mapToInt(Item::getPrice).sum();
        int expectedSafePrice = 13;

        Assert.assertEquals(expectedSafePrice, actualSafePrice);
    }
}