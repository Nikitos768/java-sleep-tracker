package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class BadQualitySessionsAnalysis implements Function<List<SleepingSession>, SleepAnaLysisResult<Long>> {
    @Override
    public SleepAnaLysisResult<Long> apply(List<SleepingSession> sessions) {
        long bedSleep = sessions.stream()
                .filter(session -> session.getQuality() == Quality.BAD)
                .count();
        return new SleepAnaLysisResult<>("Количество плохих ночей сна: ", bedSleep);
    }
}