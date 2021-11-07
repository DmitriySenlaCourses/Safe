package org.example.safe.services;

import org.example.safe.exceptions.EmptyFileException;
import org.example.safe.exceptions.IncorrectDataException;
import org.example.safe.model.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CsvFileReader {

    public int getSafeCapacity(File csvFile) throws EmptyFileException, IncorrectDataException {
        Pattern intNumberPattern = Pattern.compile("\\d+");
        int capacity = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            if (csvFile.length() == 0) {
                throw new EmptyFileException(csvFile.getAbsolutePath() + " is empty");
            }
            String capacityString = reader.readLine();
            if (intNumberPattern.matcher(capacityString).matches()) {
                capacity = Integer.parseInt(capacityString);
            } else {
                throw new IncorrectDataException("The line \"" + capacityString + "\" is incorrect");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return capacity;
    }

    public List<Item> getItemList(File csvFile) {
        List<Item> items = new ArrayList<>();
        CsvLineToItem csvLineToItem = new CsvLineToItem();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            reader.readLine();
            while (reader.ready()) {
                String itemString = reader.readLine();
                try {
                    Item item = csvLineToItem.getItem(itemString);
                    items.add(item);
                } catch (IncorrectDataException e) {
                    System.out.println(e);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return items;
    }
}
