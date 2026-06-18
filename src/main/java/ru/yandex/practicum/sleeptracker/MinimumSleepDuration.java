package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class MinimumSleepDuration implements Function<List<SleepingSession>, SleepAnaLysisResult<Long>> {
    @Override
    public SleepAnaLysisResult<Long> apply(List<SleepingSession> sessions) {
        Long minMinutes = sessions.stream()
                .mapToLong(SleepingSession::getMinutes)
                .min().orElse(0);
        return new SleepAnaLysisResult<>("Минимальная продолжительность сна в минутах: ", minMinutes);
    }
}
