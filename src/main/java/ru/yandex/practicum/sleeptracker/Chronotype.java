package ru.yandex.practicum.sleeptracker;

public enum Chronotype {
    LARK("Жаворонок"),
    OWL("Сова"),
    PIGEON("Голубь");

    private final String title;

    Chronotype(String title) { this.title = title; }

    @Override
    public String toString() { return title; }
}