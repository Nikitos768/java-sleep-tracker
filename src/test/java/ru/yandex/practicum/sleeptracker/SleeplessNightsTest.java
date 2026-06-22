package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleeplessNightsTest {
    @Test
    public void shouldReturnOneSleeplessNight() {
        SleepingSession normalSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 0),
                LocalDateTime.of(2025, 10, 2, 7, 0),
                Quality.GOOD
        );
        SleepingSession bedSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 8, 0),
                LocalDateTime.of(2025, 10, 3, 12, 0),
                Quality.GOOD
        );

        List<SleepingSession> records = List.of(normalSession, bedSession);
        SleeplessNightsAnalysis analysis = new SleeplessNightsAnalysis();

        assertEquals(1L, analysis.apply(records).getResult());
    }

    @Test
    public void shouldStartFromCurrentDayIfBeforeTwelve() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 4, 0),
                LocalDateTime.of(2025, 10, 1, 11, 0),
                Quality.GOOD
        );

        List<SleepingSession> records = List.of(session);
        SleeplessNightsAnalysis analysis = new SleeplessNightsAnalysis();

        assertEquals(0L, analysis.apply(records).getResult());
    }

    @Test
    public void shouldStartFromNextDayIfAfterTwelve() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 22, 0),
                LocalDateTime.of(2025, 10, 1, 23, 30),
                Quality.GOOD
        );

        List<SleepingSession> records = List.of(session);
        SleeplessNightsAnalysis analysis = new SleeplessNightsAnalysis();

        assertEquals(1L, analysis.apply(records).getResult());
    }

    @Test
    public void shouldCountAsSleeplessIfSleepEndsExactlyAtMidnight() {
        SleepingSession session = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 18, 0),
                LocalDateTime.of(2025, 10, 2, 0, 0),
                Quality.GOOD
        );

        List<SleepingSession> records = List.of(session);
        SleeplessNightsAnalysis analysis = new SleeplessNightsAnalysis();

        assertEquals(1L, analysis.apply(records).getResult());
    }
}