package ru.otus.HW031.display;

import java.util.List;

public interface Display {
    void displayResults(String userName, List<String[]> quizList,
                        boolean result[]);
}
