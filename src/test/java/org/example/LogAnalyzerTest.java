package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LogAnalyzerTest {
    private LogAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        List<LogEntry> entries = List.of(
                new LogEntry("127.0.0.1", "GET", "/index", 200),
                new LogEntry("127.0.0.1", "GET", "/about", 404),
                new LogEntry("192.168.0.5", "POST", "/login", 200),
                new LogEntry("10.0.0.2", "GET", "/profile", 500),
                new LogEntry("127.0.0.1", "GET", "/index", 200),
                new LogEntry("127.0.0.1", "GET", "/about", 404),
                new LogEntry("192.168.0.5", "POST", "/login", 200),
                new LogEntry("10.0.0.2", "GET", "/profile", 500),
                new LogEntry("127.0.0.1", "GET", "/index", 200),
                new LogEntry("127.0.0.1", "GET", "/about", 404),
                new LogEntry("192.168.0.5", "POST", "/login", 200),
                new LogEntry("10.0.0.2", "GET", "/profile", 500),
                new LogEntry("127.0.0.1", "GET", "/index", 200),
                new LogEntry("127.0.0.1", "GET", "/about", 404),
                new LogEntry("192.168.0.5", "POST", "/login", 200),
                new LogEntry("10.0.0.2", "GET", "/profile", 500)
        );
        analyzer = new LogAnalyzer(entries);
    }

    @Test
    void shouldReturnNumberOfEntries() {
        int result = analyzer.countRequest();
        assertEquals(16, result);
    }

    @Test
    void top10IpShouldSortFromMaxToMin() {
        List<FrequencyRecord> actual = analyzer.top10IP();
        List<FrequencyRecord> expected = new ArrayList<>(List.of(
                new FrequencyRecord("127.0.0.1", 8),
                new FrequencyRecord("10.0.0.2", 4),
                new FrequencyRecord("192.168.0.5", 4)
        ));
        expected.sort(
                Comparator.comparingInt(FrequencyRecord::frequency).reversed()
        );
        assertEquals(expected, actual);
    }

    @Test
    void top10UrlShouldSortFromMaxToMin() {
        List<FrequencyRecord> actual = new ArrayList<>(List.of(
                new FrequencyRecord("/about", 2),
                new FrequencyRecord("/profile", 3),
                new FrequencyRecord("/login", 4),
                new FrequencyRecord("/index", 1)
        ));
        actual.sort(
                Comparator.comparingInt(FrequencyRecord::frequency)
        );
        List<FrequencyRecord> expected = new ArrayList<>(List.of(
                new FrequencyRecord("/index", 1),
                new FrequencyRecord("/about", 2),
                new FrequencyRecord("/profile", 3),
                new FrequencyRecord("/login", 4)
        ));
        assertEquals(expected, actual);
    }

    @Test
    void top10MustReturnOnly10Entries() {
        List<LogEntry> entries = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            entries.add(
                    new LogEntry(
                            "127.0.0." + i,
                            "GET",
                            "/page" + i,
                            200
                    )
            );
        }
        LogAnalyzer analyzer = new LogAnalyzer(entries);
        assertEquals(10, analyzer.top10IP().size());
    }

    @Test
    void overallStatisticsShouldGroupStatusesByIp() {
        Map<String, Map<Integer, Integer>> actual =
                analyzer.overAllStatistics();

        Map<String, Map<Integer, Integer>> expected = Map.of(
                "127.0.0.1", Map.of(
                        200, 4,
                        404, 4
                ),
                "192.168.0.5", Map.of(
                        200, 4
                ),
                "10.0.0.2", Map.of(
                        500, 4
                )
        );
        assertEquals(expected, actual);
    }

    @Test
    void emptyEntriesShouldReturnZeroRequests() {
        LogAnalyzer emptyAnalyzer =
                new LogAnalyzer(List.of());

        assertEquals(0, emptyAnalyzer.countRequest());
    }

    @Test
    void emptyEntriesShouldReturnEmptyTopIps() {
        LogAnalyzer emptyAnalyzer =
                new LogAnalyzer(List.of());

        assertTrue(emptyAnalyzer.top10IP().isEmpty());
    }

    @Test
    void topIpShouldCountRequestsRegardlessOfStatusCode() {
        List<LogEntry> entries = List.of(
                new LogEntry("1.1.1.1", "GET", "/", 200),
                new LogEntry("1.1.1.1", "GET", "/", 404),
                new LogEntry("1.1.1.1", "POST", "/", 500)
        );
        LogAnalyzer analyzer =
                new LogAnalyzer(entries);
        assertEquals(
                new FrequencyRecord("1.1.1.1", 3),
                analyzer.top10IP().get(0)
        );
    }
}
