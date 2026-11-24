# LogAnalyzer

A small command-line log analysis application written in Java.

The project reads an HTTP log file, parses log entries, calculates statistics and prints a human-readable report. The resulting analysis is also serialized to JSON.

This project was built as a Java Core pet project to practice working with collections, file I/O, parsing, exceptions, records, comparators, testing and serialization.

## Features

- Reads log files from a path passed through the command line
- Parses HTTP log entries
- Counts total requests
- Calculates the percentage of accepted requests
- Finds the most frequently occurring IP addresses
- Finds the most frequently requested URLs
- Groups HTTP status codes by IP address
- Prints a formatted report to the console
- Exports the report to `report.json`
- Handles invalid log entries without terminating the whole application
- Includes JUnit tests

## Log format

Each line is expected to have the following structure:

```text
IP METHOD URL STATUS_CODE
```

Example:

```text
127.0.0.1 GET /index 200
127.0.0.1 GET /about 404
192.168.0.5 POST /login 200
10.0.0.2 GET /profile 500
```

Invalid lines are skipped and reported to `stderr`.

## Technologies

- Java 21
- Maven
- JUnit 5
- Jackson
- Git

Java Core concepts used in the project include:

- `List`
- `HashMap`
- nested maps
- `HashSet`
- `Map.Entry`
- records
- `Comparator`
- lambdas and method references
- file I/O
- exception handling

## Project structure

```text
src/main/java/org/example
├── Main.java
├── LogEntry.java
├── LogFileReader.java
├── LogParser.java
├── LogAnalyzer.java
├── AnalysisReport.java
├── FrequencyRecord.java
└── ReportPrinter.java
```

The application is divided into several responsibilities:

```text
log file
   ↓
LogFileReader
   ↓
LogParser
   ↓
List<LogEntry>
   ↓
LogAnalyzer
   ↓
AnalysisReport
   ├── ReportPrinter → console output
   └── Jackson       → report.json
```

## Build

The project uses Maven.

```bash
mvn clean package
```

The Maven Shade Plugin creates an executable JAR containing the required dependencies.

After a successful build, the application can be found in:

```text
target/LogAnalyzer-1.0-SNAPSHOT.jar
```

## Run

Pass the path to a log file as the first command-line argument:

```bash
java -jar target/LogAnalyzer-1.0-SNAPSHOT.jar logs.txt
```

You can also use an absolute path:

```bash
java -jar target/LogAnalyzer-1.0-SNAPSHOT.jar /path/to/logs.txt
```

If no path is provided, the application uses `logs.txt` from the project directory.

## Example output

```text
== LOGS ANALYSIS ==

Total entries: 16

TOP 10 IPS
127.0.0.1 -> 8
192.168.0.5 -> 4
10.0.0.2 -> 4

TOP 10 URLS
/index -> 4
/about -> 4
/login -> 4
/profile -> 4

OVERALL STATISTICS:
127.0.0.1
    200 -> 4
    404 -> 4

192.168.0.5
    200 -> 4

10.0.0.2
    500 -> 4
```

The same analysis is also written to:

```text
report.json
```

## Tests

Run the test suite with:

```bash
mvn test
```

The tests cover core `LogAnalyzer` behavior, including request counting, frequency calculation, sorting, Top 10 limits and grouped statistics.

## Purpose

This is my first Java project written from scratch.

Its main purpose was to combine individual Java Core topics into one complete application instead of practicing them only through isolated exercises.

The project helped me practice transforming raw input into structured objects, choosing appropriate data structures, separating responsibilities between classes, testing application logic and producing multiple representations of the same analysis result.

## Development
How to make changes in LogAnalyzer is in [CONTRIBUTING.md] (CONTRIBUTING.md).
