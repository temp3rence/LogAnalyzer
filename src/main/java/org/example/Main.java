package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        Path path;
        if (args.length == 0) {
            System.err.println("Usage: java -jar log-analyzer.jar <path-to-log>, currently analyzing logs.txt from project directory");
            path = Path.of("/Users/a1234/Documents/Progressmaking\\ and\\ Dayana/Java/LogAnalyzer/logs.txt\n");
        } else {
            path = Path.of(args[0]);
        }
        List<LogEntry> list = LogParser.parse(LogFileReader.readAll(path));
        LogAnalyzer analyzer = new LogAnalyzer(list);
        AnalysisReport report = new AnalysisReport(
                analyzer.countRequest(),
                analyzer.percentageOfAcceptedRequests(),
                analyzer.top10IP(),
                analyzer.top10URL(),
                analyzer.overAllStatistics()
        );
        ReportPrinter.prettyPrint(report);
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(new File("report.json"), report);
        } catch (IOException e) {
            System.err.println("failed" + e.getMessage());
        }
    }
}
