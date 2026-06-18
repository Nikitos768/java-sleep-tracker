package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class AverageSleepDuration implements Function<List<SleepingSession>, SleepAnaLysisResult<Double>> {
    @Override
    public SleepAnaLysisResult<Double> apply(List<SleepingSession> sessions) {
        double averageMinutes = sessions.stream()
                .mapToLong(SleepingSession::getMinutes)
                .average().orElse(0.0);

        return new SleepAnaLysisResult<>("Средняя продолжительность сна в минутах", averageMinutes);
    }
}
