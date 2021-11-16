package org.example.safe.services;

import org.example.safe.exceptions.EmptyFileException;
import org.example.safe.exceptions.IncorrectDataException;
import org.example.safe.model.Item;
import org.example.safe.model.dto.SafeDataDto;

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

    public SafeDataDto getInputData() throws EmptyFileException, IncorrectDataException {
        int safeCapacity = 0;
        List<Item> inputItemsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            safeCapacity = readSafeCapacity(reader);
            inputItemsList = readInputItemsList(reader);
        } catch (IOException e) {
            System.out.println(e);
        }
        return new SafeDataDto(safeCapacity, inputItemsList);
    }

    private int readSafeCapacity(BufferedReader reader) throws EmptyFileException, IncorrectDataException, IOException {
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

    private List<Item> readInputItemsList(BufferedReader reader) throws IOException, IncorrectDataException {
        List<Item> inputItemsList = new ArrayList<>();
        CsvLineConverter csvLineConverter = new CsvLineConverter();
        while (reader.ready()) {
            String sourceString = reader.readLine();
            Item item = csvLineConverter.toItem(sourceString);
            inputItemsList.add(item);
        }
        return inputItemsList;
    }
}
