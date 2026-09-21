package org.example;

public class LogEntry {
    private String ip;
    private String method;
    private String url;
    private int statusCode;

    public LogEntry(String ip, String method, String url, int statusCode) {
        this.ip = ip;
        this.method = method;
        this.url = url;
        this.statusCode = statusCode;
    }

    public String getIp() {
        return ip;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}
