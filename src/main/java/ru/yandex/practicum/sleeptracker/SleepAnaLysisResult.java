package ru.yandex.practicum.sleeptracker;

public class SleepAnaLysisResult<T> {
    private final String answer;
    private final T result;

    public SleepAnaLysisResult(String answer, T result) {
        this.answer = answer;
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        String formattedResult = (result instanceof Double)
                ? String.format("%.2f", result)
                : String.valueOf(result);

        return answer + " " + formattedResult;
    }
}
