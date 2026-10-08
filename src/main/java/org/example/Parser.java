package org.example;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Parser(){}
    public static List<LogEntry> parse(List<String> logs) {
        List<LogEntry> list = new ArrayList<>();
        for (String entry : logs) {
            String[] entrysplit = entry.split(" ");
            String ip = entrysplit[0];
            String method = entrysplit[1];
            String url = entrysplit[2];
            int statusCode = Integer.parseInt(entrysplit[3]);
            LogEntry e = new LogEntry(ip, method, url, statusCode);
            list.add(e);
        }
        return list;
    }
}
