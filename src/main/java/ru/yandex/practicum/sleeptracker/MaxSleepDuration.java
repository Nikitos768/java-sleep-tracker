package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class MaxSleepDuration implements Function<List<SleepingSession>, SleepAnaLysisResult<Long>> {
    @Override
    public SleepAnaLysisResult<Long> apply(List<SleepingSession> sessions) {
        long maxMinutes = sessions.stream()
                .mapToLong(SleepingSession::getMinutes)
                .max().orElse(0);
        return new SleepAnaLysisResult<>("Максимальная продолжительность сна в минутах: ", maxMinutes);
    }
}
