package ru.yandex.practicum.sleeptracker;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChronotypeAnalysis implements Function<List<SleepingSession>, SleepAnaLysisResult<Chronotype>> {
    @Override
    public  SleepAnaLysisResult<Chronotype> apply(List<SleepingSession> sessions) {
        Map<Chronotype, Long> counts = sessions.stream()
                .filter(session -> !session.getStart().toLocalDate().equals(session.getEnd().toLocalDate()))
                .map(ChronotypeAnalysis::determineNightType)
                .collect(Collectors.groupingBy(type -> type, Collectors.counting()));

        long owlCount = counts.getOrDefault(Chronotype.OWL, 0L);
        long larkCount = counts.getOrDefault(Chronotype.LARK, 0L);
        long pigeonCount = counts.getOrDefault(Chronotype.PIGEON, 0L);

        boolean preferOwl = owlCount > larkCount && owlCount > pigeonCount;
        boolean preferLark = larkCount > owlCount && larkCount > pigeonCount;

        Chronotype finalType = preferOwl ? Chronotype.OWL : (preferLark ? Chronotype.LARK : Chronotype.PIGEON);
        return new SleepAnaLysisResult<>("Классификация пользователя: ", finalType);
    }

    public static Chronotype determineNightType(SleepingSession session) {
        int startHour = session.getStart().getHour();
        int endHour = session.getEnd().getHour();

        boolean isOwl = startHour >= 23 && endHour >= 9;
        boolean isLark = startHour < 22 && endHour < 7;

        return isOwl ? Chronotype.OWL : (isLark ? Chronotype.LARK : Chronotype.PIGEON);
    }}
