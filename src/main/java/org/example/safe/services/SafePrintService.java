package org.example.safe.services;

import org.example.safe.model.Item;
import org.example.safe.model.Safe;

public class SafePrintService {

    public void printVolumeSafeItems(Safe safe) {
        System.out.println("Volume items = " + safe.getItems().stream().mapToInt(Item::getVolume).sum());
    }

    public void printSafe(Safe safe) {
        System.out.println(safe);
    }

    public void printPriceSafe(Safe safe) {
        System.out.println("Safe price = " + safe.getItems().stream().mapToInt(Item::getPrice).sum());
    }
}
