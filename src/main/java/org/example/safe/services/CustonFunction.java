package org.example.safe.services;

import org.example.safe.exceptions.EmptyFileException;
import org.example.safe.exceptions.IncorrectDataException;

import java.io.IOException;

public interface CustonFunction<T, R> {
    R apply(T t) throws EmptyFileException, IncorrectDataException, IOException;
}
