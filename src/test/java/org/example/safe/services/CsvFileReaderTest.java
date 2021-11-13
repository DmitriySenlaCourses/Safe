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

    @Test
    public void getSafeCapacity() throws EmptyFileException, IncorrectDataException {
        File file = new File("src\\test\\resources\\data.csv");
        int expectedCapacity = 13;

        CsvFileReader reader = new CsvFileReader(file);
        int actualCapacity = reader.getSafeCapacity();

        Assert.assertEquals(expectedCapacity, actualCapacity);

    }

    @Test(expected = EmptyFileException.class)
    public void getSafeCapacityEmptyFileException() throws EmptyFileException, IncorrectDataException {
        File file = new File("src\\test\\resources\\data5.csv");

        CsvFileReader reader = new CsvFileReader(file);
        int actualCapacity = reader.getSafeCapacity();
    }

    @Test(expected = IncorrectDataException.class)
    public void getSafeCapacityIncorrectDataException() throws EmptyFileException, IncorrectDataException {
        File file = new File("src\\test\\resources\\data4.csv");

        CsvFileReader reader = new CsvFileReader(file);
        int actualCapacity = reader.getSafeCapacity();
    }

    @Test
    public void getItemList() throws EmptyFileException, IncorrectDataException {
        File file = new File("src\\test\\resources\\data.csv");
        List<Item> expectedItemList = new ArrayList<>();
        expectedItemList.add(new Item("item1",3,1));
        expectedItemList.add(new Item("item2",4,6));
        expectedItemList.add(new Item("item3",5,4));
        expectedItemList.add(new Item("item4",8,7));
        expectedItemList.add(new Item("item5",9,6));

        CsvFileReader reader = new CsvFileReader(file);
        List<Item> actualItemList = reader.getItemList();
        Assert.assertEquals(expectedItemList, actualItemList);
    }
}