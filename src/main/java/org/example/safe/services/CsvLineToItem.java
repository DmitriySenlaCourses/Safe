package org.example.safe.services;

import org.example.safe.model.Item;

public class CsvLineToItem {

    public static Item getItem(String csvLine) {
        String[] split = csvLine.split(";");
        if (split.length < 3) {
            throw new IllegalArgumentException("The line \"" + csvLine + "\" is incorrect");
        }
        Item item;
        try {
            item = new Item(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The line \"" + csvLine + "\" is incorrect");
        }
        return item;
    }
}
