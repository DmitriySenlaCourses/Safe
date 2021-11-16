package org.example.safe.services;

import org.example.safe.exceptions.EmptyFileException;
import org.example.safe.exceptions.IncorrectDataException;
import org.example.safe.model.Item;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CsvFileReaderTest {

    @Test(expected = EmptyFileException.class)
    public void getInputDataEmptyFileException() throws EmptyFileException, IncorrectDataException {
        File file = new File("src\\test\\resources\\data5.csv");

        CsvFileReader reader = new CsvFileReader(file);
        int actualCapacity = reader.getInputData().getSafeCapacity();
    }

    @Test(expected = IncorrectDataException.class)
    public void getInputDataIncorrectDataException() throws EmptyFileException, IncorrectDataException {
        File file = new File("src\\test\\resources\\data4.csv");

        CsvFileReader reader = new CsvFileReader(file);
        int actualCapacity = reader.getInputData().getSafeCapacity();
    }

    @Test
    public void getInputData() throws EmptyFileException, IncorrectDataException {
        File file = new File("src\\test\\resources\\data.csv");
        int expectedCapacity = 13;
        List<Item> expectedItemsList = new ArrayList<>();
        expectedItemsList.add(new Item("item1",3,1));
        expectedItemsList.add(new Item("item2",4,6));
        expectedItemsList.add(new Item("item3",5,4));
        expectedItemsList.add(new Item("item4",8,7));
        expectedItemsList.add(new Item("item5",9,6));

        CsvFileReader reader = new CsvFileReader(file);
        int actualCapacity = reader.getInputData().getSafeCapacity();
        List<Item> actualInputItemsList = reader.getInputData().getItems();

        Assert.assertEquals(expectedCapacity, actualCapacity);
        Assert.assertEquals(expectedItemsList, actualInputItemsList);
    }
}