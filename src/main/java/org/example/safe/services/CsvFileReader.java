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
    private final File sourceFile;
    private final Pattern intNumberPattern = Pattern.compile("\\d+");

    public CsvFileReader(File sourceFile) {
        this.sourceFile = sourceFile;
    }

    public int getSafeCapacity() throws EmptyFileException, IncorrectDataException {
        return (int) csvReader(this::readCapacity);
    }

    public List<Item> getItemList() throws EmptyFileException, IncorrectDataException {
        return (List<Item>) csvReader(this::readItems);
    }

    private Object csvReader(CustonFunction<BufferedReader, ?> function) throws EmptyFileException, IncorrectDataException {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            return function.apply(reader);
        } catch (IOException e) {
            System.out.println(e);
        }
        return null;
    }

    private int readCapacity(BufferedReader reader) throws EmptyFileException, IOException, IncorrectDataException {
        if (sourceFile.length() == 0) {
            throw new EmptyFileException(sourceFile.getAbsolutePath() + " is empty");
        }
        String capacityString = reader.readLine();
        if (intNumberPattern.matcher(capacityString).matches()) {
            return Integer.parseInt(capacityString);
        } else {
            throw new IncorrectDataException("The line \"" + capacityString + "\" is incorrect");
        }
    }

    private List<Item> readItems(BufferedReader reader) throws IOException {
        List<Item> items = new ArrayList<>();
        CsvLineConverter csvLineConverter = new CsvLineConverter();
        reader.readLine();
        while (reader.ready()) {
            String sourceString = reader.readLine();
            try {
                Item item = csvLineConverter.toItem(sourceString);
                items.add(item);
            } catch (IncorrectDataException e) {
                System.out.println(e);
            }
        }
        return items;
    }
}
