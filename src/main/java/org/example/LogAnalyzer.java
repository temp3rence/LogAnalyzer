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

    public String getMostPopularIp(List<LogEntry> logs) {
        Map<String, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        String result = "";
        for (LogEntry entry : logs) {
            map.put(entry.getIp(), map.getOrDefault(entry.getIp(), 0) + 1);
            if (map.get(entry.getIp()) > max) {
                max = map.get(entry.getIp());
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                result = entry.getKey();
            }
        }
        return result;
    }

    public int percentageOfAcceptedRequests(List<LogEntry> list) {
        float allRequestsSum = 0;
        float acceptedRequestsSum = 0;
        for (LogEntry a : list) {
            allRequestsSum++;
            if (a.getStatusCode() >= 200 && a.getStatusCode() < 300) {
                acceptedRequestsSum++;
            }
        }
        return (int) (acceptedRequestsSum/allRequestsSum * 100);
    }

    public Map<String, Map<Integer, Integer>> overAllStatistics(List<LogEntry> list) {
        Map<String, Map<Integer, Integer>> map = new HashMap<>();
        for (LogEntry entry : list) {
            String ip = entry.getIp();
            int status = entry.getStatusCode();
            Map<Integer, Integer> innerMap = map.computeIfAbsent(ip, k -> new HashMap<>());
            innerMap.put(status, innerMap.getOrDefault(status, 0) + 1);
        }
        return map;
    }
}
