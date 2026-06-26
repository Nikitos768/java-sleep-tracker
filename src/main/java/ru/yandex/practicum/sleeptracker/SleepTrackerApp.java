package ru.yandex.practicum.sleeptracker;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SleepTrackerApp {

    private static final List<Function<List<SleepingSession>, SleepAnaLysisResult<?>>> analyses = List.of(
            records -> new TotalSessionsAnalysis().apply(records),
            records -> new MinimumSleepDuration().apply(records),
            records -> new MaxSleepDuration().apply(records),
            records -> new AverageSleepDuration().apply(records),
            records -> new BadQualitySessionsAnalysis().apply(records),
            records -> new SleeplessNightsAnalysis().apply(records),
            records -> new ChronotypeAnalysis().apply(records)
    );

    public static void main(String[] args) {
        try {
            String filePath = (args.length > 0) ? args[0] : "src/main/resources/sleep_log.txt";
            List<SleepingSession> records = readSleepData(filePath);
            analyses.stream()
                    .filter(analysis -> !records.isEmpty())
                    .forEach(analysis -> {
                        SleepAnaLysisResult<?> result = analysis.apply(records);
                        System.out.println(result);
                    });
        } catch (RuntimeException e) {
            System.out.print("Произошла ошибка при выполнении программы: " + e.getMessage());
        }
    }

    public static List<SleepingSession> readSleepData(String filePath) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

        try (Stream<String> lines = Files.lines(Path.of(filePath))) {
            return lines
                    .filter(line -> !line.trim().isEmpty())
                    .map(line -> {
                        String[] parse = line.split(";");
                        LocalDateTime start = LocalDateTime.parse(parse[0].trim(), formatter);
                        LocalDateTime end = LocalDateTime.parse(parse[1].trim(), formatter);
                        Quality quality = Quality.valueOf(parse[2].trim());
                        return new SleepingSession(start, end, quality);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать файл: " + filePath, e);
        }
    }
}