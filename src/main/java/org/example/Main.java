package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        Path path = Path.of("logs.txt");
        List<LogEntry> list = LogParser.parse(LogFileReader.readAll(path));
        LogAnalyzer analisator = new LogAnalyzer(list);
        AnalysisReport report = new AnalysisReport(
                analisator.countRequest(),
                analisator.percentageOfAcceptedRequests(),
                analisator.top10IP(),
                analisator.top10URL(),
                analisator.overAllStatistics()
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
