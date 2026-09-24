package org.example;

import java.util.*;

public class LogAnalyzer {

    private List<LogEntry> entries;

    public LogAnalyzer(List<LogEntry> list) {
        this.entries = list;
    }

    public int countRequest(List<LogEntry> logs) {
        return logs.size();
    }

    public Set<String> getUniqueIps(List<LogEntry> logs) {
        Set<String> set = new HashSet<>();
        for (LogEntry entry: logs) {
            set.add(entry.getIp());
        }
        return set;
    }

    public Map<Integer, Integer> getStatusCodes(List<LogEntry> logs) {
        Map<Integer, Integer> map = new HashMap<>();
        for (LogEntry entry : logs) {
            map.put(entry.getStatusCode(), map.getOrDefault(entry.getStatusCode(), 0) + 1);
        }
        return map;
    }

    public Map<String, Integer> getUrlRequests(List<LogEntry> logs) {
        Map<String, Integer> map = new HashMap<>();
        for (LogEntry entry : logs) {
            map.put(entry.getUrl(), map.getOrDefault(entry.getUrl(), 0) + 1);
        }
        return map;
    }

    public Map<String, Integer> getRequestsFromIps(List<LogEntry> logs) {
        Map<String, Integer> map = new HashMap<>();
        for (LogEntry entry : logs) {
            map.put(entry.getIp(), map.getOrDefault(entry.getIp(), 0) + 1);
        }
        return map;
    }
}
