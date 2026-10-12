package org.example;

import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("logs.txt");
        List<LogEntry> list = LogParser.parse(LogFileReader.readAll(path));
        LogAnalyzer analisator = new LogAnalyzer(list);
        System.out.println(analisator.getStatusCodes());
        System.out.println(analisator.getMostPopularIp());
        System.out.println(analisator.percentageOfAcceptedRequests());
        System.out.println(analisator.overAllStatistics());
        System.out.println(analisator.top10IP());
        System.out.println(analisator.top10URL());
    }
}
