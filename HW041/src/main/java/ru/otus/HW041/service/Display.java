package ru.otus.HW041.service;

import java.util.List;

public interface Display {
    void displayResults(String userName, List<String[]> quizList,
                        boolean result[]);
}
