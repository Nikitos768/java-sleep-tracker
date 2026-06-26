package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChronotypeAnalysisTest {

    @Test
    public void shouldReturnOwlChronotype() {
        SleepingSession owlNight = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 30), // после 23:00
                LocalDateTime.of(2025, 10, 2, 9, 30),  // после 09:00
                Quality.GOOD
        );

        List<SleepingSession> records = List.of(owlNight);
        ChronotypeAnalysis analysis = new ChronotypeAnalysis();

        assertEquals(Chronotype.OWL, analysis.apply(records).getResult());
    }

    @Test
    public void shouldReturnLarkChronotype() {
        SleepingSession larkNight = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 21, 30), // до 22:00
                LocalDateTime.of(2025, 10, 2, 6, 30),  // до 07:00
                Quality.GOOD
        );

        List<SleepingSession> records = List.of(larkNight);
        ChronotypeAnalysis analysis = new ChronotypeAnalysis();

        assertEquals(Chronotype.LARK, analysis.apply(records).getResult());
    }

    @Test
    public void shouldReturnPigeonIfCountsAreEqual() {
        SleepingSession owlNight = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 30),
                LocalDateTime.of(2025, 10, 2, 10, 0),
                Quality.GOOD
        );
        SleepingSession larkNight = new SleepingSession(
                LocalDateTime.of(2025, 10, 3, 21, 0),
                LocalDateTime.of(2025, 10, 4, 6, 0),
                Quality.GOOD
        );

        List<SleepingSession> records = List.of(owlNight, larkNight);
        ChronotypeAnalysis analysis = new ChronotypeAnalysis();

        assertEquals(Chronotype.PIGEON, analysis.apply(records).getResult());
    }
}
