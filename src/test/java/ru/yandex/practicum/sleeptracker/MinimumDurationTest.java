package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumDurationTest {
    @Test
    public void shouldReturnMinimumDuration() {
        SleepingSession shortSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 10, 0),
                LocalDateTime.of(2025, 10, 1, 11, 0),
                Quality.GOOD
        );
        SleepingSession longSession = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 10, 0),
                LocalDateTime.of(2025, 10, 1, 12, 0),
                Quality.GOOD
        );

        List<SleepingSession> records = List.of(shortSession, longSession);
        MinimumSleepDuration analysis = new MinimumSleepDuration();

        assertEquals(60L, analysis.apply(records).getResult());
    }

    @Test
    public void shouldReturnZeroForEmptyList() {
        MinimumSleepDuration analysis = new MinimumSleepDuration();
        assertEquals(0L, analysis.apply(List.of()).getResult());
    }
}
