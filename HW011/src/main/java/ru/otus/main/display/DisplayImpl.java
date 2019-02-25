package ru.otus.main.display;

import ru.otus.main.quiz.Quiz;
import java.util.List;

public class DisplayImpl implements Display{
    public void displayResults(boolean[] result, Quiz quiz) {
        System.out.println("Results:");

        List<String[]> quizList = quiz.getQuiz();
        for(int i = 0; i < quizList.size(); i++) {
            System.out.println(quizList.get(i)[0] + " : " +
                    quizList.get(i)[1] + " " +
                    (result[i] ? "+" : "-"));
        }
    }
}
