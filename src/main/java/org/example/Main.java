package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String[] logs = {
                "127.0.0.1 GET /index 200",
                "127.0.0.1 GET /about 404",
                "192.168.0.5 POST /login 200",
                "10.0.0.2 GET /profile 500",
                "192.168.0.5 GET /index 200"
        };
        System.out.println(countRequest(logs));
        System.out.println(getUniqueIps(logs));
        System.out.println(getStatusCodes(logs));
        System.out.println(getUrlRequests(logs));
        System.out.println(getRequestsFromIps(logs));
    }

    public static int countRequest(String[] logs) {
        return logs.length;
    }

    public static Set<String> getUniqueIps(String[] logs) {
        Set<String> set = new HashSet<>();
        for (String str : logs) {
            String[] splitted = str.split(" ");
            set.add(splitted[0]);
        }
        return set;
    }

    public static Map<String, Integer> getStatusCodes(String[] logs) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : logs) {
            String[] splitted = str.split(" ");
            map.put(splitted[3], map.getOrDefault(splitted[3], 0) + 1);
        }
        return map;
    }

    public static Map<String, Integer> getUrlRequests(String[] logs) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : logs) {
            String[] splitted = str.split(" ");
            map.put(splitted[2], map.getOrDefault(splitted[2], 0) + 1);
        }
        return map;
    }

    public static Map<String, Integer> getRequestsFromIps(String[] logs) {
        Map<String, Integer> map = new HashMap<>();
        for (String str : logs) {
            String[] splitted = str.split(" ");
            map.put(splitted[0], map.getOrDefault(splitted[0], 0) + 1);
        }
        return map;
    }
}