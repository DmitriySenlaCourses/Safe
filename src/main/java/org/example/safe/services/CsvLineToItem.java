package org.example.safe.services;

import org.example.safe.exceptions.IncorrectDataException;
import org.example.safe.model.Item;

import java.util.regex.Pattern;

public class CsvLineToItem {

    public Item getItem(String csvLine) throws IncorrectDataException {
        Pattern itemStringPattern = Pattern.compile(".+;\\d+;\\d+");

        if (!itemStringPattern.matcher(csvLine).matches()) {
            throw new IncorrectDataException("The line \"" + csvLine + "\" is incorrect");
        } else {
            String[] split = csvLine.split(";");
            return new Item(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        }
    }
}
