package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class TotalSessionsAnalysis implements Function<List<SleepingSession>, SleepAnaLysisResult<Integer>> {
    @Override
    public SleepAnaLysisResult<Integer> apply(List<SleepingSession> records) {
        return new SleepAnaLysisResult("Количество сессий сна: ", records.size());
    }
}
