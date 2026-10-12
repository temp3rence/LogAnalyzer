package org.example;

import java.util.ArrayList;
import java.util.List;

public class LogParser {
    private LogParser(){}
    public static List<LogEntry> parse(List<String> logs) {
        List<LogEntry> list = new ArrayList<>();
        int count = 0;
        for (String entry : logs) {
            if (entry.isBlank()) {
                continue;
            }
            count++;
            try {
                String stripped = entry.strip();
                String[] entrysplit = stripped.split("\\s+");
                if (entrysplit.length != 4) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                String ip = entrysplit[0];
                String method = entrysplit[1].toUpperCase();
                String url = entrysplit[2];
                int statusCode;
                try {
                    statusCode = Integer.parseInt(entrysplit[3]);
                } catch (NumberFormatException e) {
                    System.err.printf("%d: status code is not a number, line skipped\n", count);
                    continue;
                }

                if (statusCode < 100 || statusCode > 599) {
                    System.err.printf("%d: invalid HTTP status code, line skipped%n", count);
                    continue;
                }
                LogEntry e = new LogEntry(ip, method, url, statusCode);
                list.add(e);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.printf("%d: wrong format, line skipped\n", count);
            } catch (NumberFormatException e) {
                System.err.printf("%d: integer couldn't parse, line skipped\n", count);
            }
        }
        return list;
    }
}
