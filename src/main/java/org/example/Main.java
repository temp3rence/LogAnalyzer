package org.example;

import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("logs.txt");
        List<LogEntry> list = Parser.parse(LogFileReader.readAll(path));
        LogAnalyzer analisator = new LogAnalyzer(list);
        System.out.println(analisator.getStatusCodes(list));
        System.out.println(analisator.getMostPopularIp(list));
        System.out.println(analisator.percentageOfAcceptedRequests(list));
        System.out.println(analisator.overAllStatistics(list));
    }
}
