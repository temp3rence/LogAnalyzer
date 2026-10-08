package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LogFileReader {

    private LogFileReader(){}

    public static List<String> readAll(Path path) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            System.err.println("file not found " + e.getMessage());
        }
        return lines;
    }
}