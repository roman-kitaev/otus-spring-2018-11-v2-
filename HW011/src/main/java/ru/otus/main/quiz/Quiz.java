package ru.otus.main.quiz;

import java.util.List;

public class Quiz {
    private List<String[]> quiz;

    public Quiz(List<String[]> quiz) {
        this.quiz = quiz;
    }

    public List<String[]> getQuiz() {
        return quiz;
    }
}
