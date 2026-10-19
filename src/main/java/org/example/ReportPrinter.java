package org.example;

import java.util.Map;

public class ReportPrinter {
    private ReportPrinter() {}

    public static void prettyPrint(AnalysisReport record) {
        System.out.println("== LOGS ANALYSIS==");
        System.out.println();
        System.out.printf("Total entries: %d\n", record.totalEntries());
        System.out.println("TOP 10 IPS");
        System.out.println("ip\t\t\tfrequency");
        for (FrequencyRecord frequencies : record.topIps()) {
            System.out.printf("%s -> %d\n", frequencies.value(), frequencies.frequency());
        }
        System.out.println();
        System.out.println("TOP 10 URLS");
        System.out.println("url\t\tfrequency");
        for (FrequencyRecord frequencies : record.topUrls()) {
            System.out.printf("%s -> \t %d\n", frequencies.value(), frequencies.frequency());
        }
        System.out.println();
        System.out.println("OVERALL STATISTICS:");
        for (Map.Entry<String, Map<Integer, Integer>> outerEntry : record.overallStatistics().entrySet()) {
            System.out.println(outerEntry.getKey());
            Map<Integer,Integer> innerMap = outerEntry.getValue();
            for (Map.Entry<Integer, Integer> innerEntry : innerMap.entrySet()) {
                System.out.printf("\t%d -> %d\n", innerEntry.getKey(), innerEntry.getValue());
            }
        }

        System.out.println("the end.");
    }
}
