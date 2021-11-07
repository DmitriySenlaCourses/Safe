package org.example.safe.services;

import org.example.safe.model.Item;
import org.example.safe.model.Safe;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

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

        List<Item> optimalItemList = new ArrayList<>();
        optimalItemList.add(new Item("item2",4,6));
        optimalItemList.add(new Item("item4",8,7));

        SafeService safeService = new SafeService();
        Safe fillingSafe = safeService.fillSafe(safe, itemList);

        int actualSafePrice = fillingSafe.getItems().stream().mapToInt(Item::getPrice).sum();
        int expectedSafePrice = optimalItemList.stream().mapToInt(Item::getPrice).sum();

        Assert.assertEquals(expectedSafePrice, actualSafePrice);
    }
}