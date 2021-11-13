package org.example.safe.services;

import org.example.safe.exceptions.IncorrectDataException;
import org.example.safe.model.Item;

import java.util.regex.Pattern;

public class CsvLineConverter {
    private final Pattern itemStringPattern = Pattern.compile(".+;\\d+;\\d+");

    public Item toItem(String sourceString) throws IncorrectDataException {

        if (!itemStringPattern.matcher(sourceString).matches()) {
            throw new IncorrectDataException("The line \"" + sourceString + "\" is incorrect");
        } else {
            String[] split = sourceString.split(";");
            return new Item(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        }
    }
}
