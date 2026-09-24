package org.example;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private String[] logs;

    public Parser(String[] logs) {
        this.logs = logs;
    }

    public static List<LogEntry> parse(String[] logs) {
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
