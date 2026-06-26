package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

public class SleepTrackerAppTest {

    @Test
    public void shouldReturnCorrectSessionsCount() {
        SleepingSession oneSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 1, 23, 30),
                Quality.GOOD
        );
        SleepingSession twoSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 10, 0),
                LocalDateTime.of(2025, 10, 1, 12, 0),
                Quality.GOOD
        );

        TotalSessionsAnalysis analysis = new TotalSessionsAnalysis();
        List<SleepingSession> records = List.of(oneSession, twoSession);

        assertEquals(2, analysis.apply(records).getResult());
    }

    @Test
    public void shouldReturnCorrectDescriptionText() {
        SleepingSession twoSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 10, 0),
                LocalDateTime.of(2025, 10, 1, 12, 0),
                Quality.GOOD
        );

        TotalSessionsAnalysis analysis = new TotalSessionsAnalysis();
        List<SleepingSession> records = List.of(twoSession);

        assertEquals("Количество сессий сна: ", analysis.apply(records).getAnswer());
    }
}