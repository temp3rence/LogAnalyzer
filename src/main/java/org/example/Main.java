package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] rawLogs = {
                "127.0.0.1 GET /index 200",
                "127.0.0.1 GET /about 404",
                "192.168.0.5 POST /login 200",
                "10.0.0.2 GET /profile 500",
                "192.168.0.5 GET /index 200"
        };
        List<LogEntry> list = Parser.parse(rawLogs);
        LogAnalyzer analisator = new LogAnalyzer(list);
        System.out.println(analisator.getStatusCodes(list));
    }
}
