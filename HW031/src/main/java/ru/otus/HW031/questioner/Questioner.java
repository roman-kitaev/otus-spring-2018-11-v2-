package ru.otus.HW031.questioner;

import ru.otus.HW031.quiz.Quiz;

import java.io.InputStream;

public interface Questioner {
    void ask(InputStream in, Quiz quiz);
}
