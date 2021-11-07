package org.example.safe.services;

import org.example.safe.exceptions.EmptyFileException;
import org.example.safe.exceptions.IncorrectDataException;
import org.example.safe.model.Item;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CsvFileReaderTest {

    @Test
    public void getSafeCapacity() throws EmptyFileException, IncorrectDataException {
        File file = new File("src\\test\\resources\\data.csv");
        int expectedCapacity = 13;

        CsvFileReader reader = new CsvFileReader();
        int actualCapacity = reader.getSafeCapacity(file);

        Assert.assertEquals(expectedCapacity, actualCapacity);

    }

    @Test(expected = EmptyFileException.class)
    public void getSafeCapacityEmptyFileException() throws EmptyFileException, IncorrectDataException {
        File file = new File("src\\test\\resources\\data5.csv");

        CsvFileReader reader = new CsvFileReader();
        int actualCapacity = reader.getSafeCapacity(file);
    }

    @Test(expected = IncorrectDataException.class)
    public void getSafeCapacityIncorrectDataException() throws EmptyFileException, IncorrectDataException {
        File file = new File("src\\test\\resources\\data4.csv");

        CsvFileReader reader = new CsvFileReader();
        int actualCapacity = reader.getSafeCapacity(file);
    }

    @Test
    public void getItemList() {
        File file = new File("src\\test\\resources\\data.csv");
        List<Item> expectedItemList = new ArrayList<>();
        expectedItemList.add(new Item("item1",3,1));
        expectedItemList.add(new Item("item2",4,6));
        expectedItemList.add(new Item("item3",5,4));
        expectedItemList.add(new Item("item4",8,7));
        expectedItemList.add(new Item("item5",9,6));

        CsvFileReader reader = new CsvFileReader();
        List<Item> actualItemList = reader.getItemList(file);
        Assert.assertEquals(expectedItemList, actualItemList);
    }
}