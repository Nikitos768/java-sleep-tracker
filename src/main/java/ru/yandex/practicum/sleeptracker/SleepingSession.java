package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;

public class SleepingSession {
    private final LocalDateTime start;
    private final LocalDateTime end;
    private final Quality quality;


    public SleepingSession(LocalDateTime start, LocalDateTime end, Quality quality) {
        this.start = start;
        this.end = end;
        this.quality = quality;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public Quality getQuality() {
        return quality;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public long getMinutes() {
        return Duration.between(start, end).toMinutes();
    }

}