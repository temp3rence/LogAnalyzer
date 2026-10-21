package org.example;

import java.util.List;
import java.util.Map;

public record AnalysisReport(
        int totalEntries,
        int percentage,
        List<FrequencyRecord> topIps,
        List<FrequencyRecord> topUrls,
        Map<String, Map<Integer, Integer>> overallStatistics) {}
