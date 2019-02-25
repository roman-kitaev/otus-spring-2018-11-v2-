package ru.otus.HW041.data;

import java.util.List;

public class Quiz {
    private String name;
    private List<String[]> quiz;
    private boolean[] result;

    public Quiz(String name, List<String[]> quiz) {
        this.name = name;
        this.quiz = quiz;
    }

    public String getName() {
        return name;
    }

    public List<String[]> getQuiz() {
        return quiz;
    }

    public boolean[] getResult() {
        if(result == null) {
            throw new ResultNotReadyException();
        }
        return result;
    }

    public void setResult(boolean[] result) {
        this.result = result;
    }
}
