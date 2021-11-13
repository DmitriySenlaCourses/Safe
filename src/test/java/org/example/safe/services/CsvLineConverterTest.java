package org.example.safe.services;

import org.example.safe.exceptions.IncorrectDataException;
import org.example.safe.model.Item;
import org.junit.Assert;
import org.junit.Test;

public class CsvLineConverterTest {
    private final CsvLineConverter csvLineConverter = new CsvLineConverter();

    @Test
    public void getItem() throws IncorrectDataException {
        String csvLine = "item1;3;1";
        Item expectedItem = new Item("item1", 3, 1);

        Item actualItem = csvLineConverter.toItem(csvLine);

        Assert.assertEquals(expectedItem, actualItem);
    }

    @Test(expected = IncorrectDataException.class)
    public void getItemIncorrectDataException() throws IncorrectDataException {
        String csvLine = "item1;3;1d";

        Item actualItem = csvLineConverter.toItem(csvLine);

    }
}