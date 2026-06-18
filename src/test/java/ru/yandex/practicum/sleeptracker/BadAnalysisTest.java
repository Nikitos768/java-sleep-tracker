package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadAnalysisTest {
    @Test
    public void shouldReturnNumberSessionsDuration() {
        SleepingSession oneSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 10, 0),
                LocalDateTime.of(2025, 10, 1, 11, 0),
                Quality.BAD
        );
        SleepingSession twoSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 10, 0),
                LocalDateTime.of(2025, 10, 1, 12, 0),
                Quality.GOOD
        );
        SleepingSession freeSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 10, 0),
                LocalDateTime.of(2025, 10, 1, 12, 0),
                Quality.BAD
        );
        SleepingSession fourSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 10, 0),
                LocalDateTime.of(2025, 10, 1, 12, 0),
                Quality.NORMAL
        );

        List<SleepingSession> records = List.of(oneSession, twoSession, freeSession, fourSession);
        BadQualitySessionsAnalysis analysis = new BadQualitySessionsAnalysis();

        assertEquals(2L, analysis.apply(records).getResult());
    }

    @Test
    public void shouldReturnZeroForEmptyList() {
        BadQualitySessionsAnalysis analysis = new BadQualitySessionsAnalysis();
        assertEquals(0L, analysis.apply(List.of()).getResult());
    }
}
