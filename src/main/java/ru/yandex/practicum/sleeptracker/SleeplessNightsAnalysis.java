package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public class SleeplessNightsAnalysis implements Function<List<SleepingSession>, SleepAnaLysisResult<Long>> {
    @Override
    public SleepAnaLysisResult<Long> apply(List<SleepingSession> sessions) {
        LocalDateTime absoluteStart = sessions.stream()
                .map(SleepingSession::getStart)
                .min(LocalDateTime::compareTo)
                .orElse(LocalDateTime.now());

        LocalDate findNightDate = absoluteStart.getHour() >= 12
                ? absoluteStart.toLocalDate().plusDays(1)
                : absoluteStart.toLocalDate();

        LocalDateTime absoluteEnd = sessions.stream()
                .map(SleepingSession::getEnd)
                .max(LocalDateTime::compareTo)
                .orElse(LocalDateTime.now());

        LocalDate lastNightDate = absoluteEnd.toLocalDate().isBefore(findNightDate)
                ? findNightDate
                : absoluteEnd.toLocalDate();

        long totalDays = java.time.temporal.ChronoUnit.DAYS.between(findNightDate, lastNightDate);

        long sleeplessNights = java.util.stream.LongStream.rangeClosed(0, totalDays)
                .mapToObj(i -> findNightDate.plusDays(i))
                .filter(date -> {
                    LocalDateTime nightStart = date.atStartOfDay();
                    LocalDateTime nightEnd = date.atTime(6, 0);
                    return sessions.stream().noneMatch(session ->
                            session.getStart().isBefore(nightEnd) && session.getEnd().isAfter(nightStart)
                    );
                }).count();
        return new SleepAnaLysisResult<>("Количество бессонных ночей: ", sleeplessNights);
    }
}