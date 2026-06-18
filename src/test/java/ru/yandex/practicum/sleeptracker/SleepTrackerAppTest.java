package ru.yandex.practicum.sleeptracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class SleepTrackerAppTest {

    @Test
    public void shouldReturnCorrectSessionsCount() {
        List<SleepingSession> testRecords = List.of(
                new SleepingSession(null, null, null),
                new SleepingSession(null, null, null)
        );

        TotalSessionsAnalysis analysis = new TotalSessionsAnalysis();

        SleepAnaLysisResult<Integer> result = analysis.apply(testRecords);

        assertEquals(2, result.getResult());
    }

    @Test
    public void shouldReturnCorrectDescriptionText() {
        List<SleepingSession> testRecords = List.of(
                new SleepingSession(null, null, null)
        );

        TotalSessionsAnalysis analysis = new TotalSessionsAnalysis();
        SleepAnaLysisResult<Integer> result = analysis.apply(testRecords);

        assertEquals("Количество сессий сна: ", result.getAnswer());
    }
}