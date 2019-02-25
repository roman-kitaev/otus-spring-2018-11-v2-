package ru.otus.main.questioner;

import ru.otus.main.quiz.Quiz;

import java.io.InputStream;

public interface Questioner {
    boolean[] ask(InputStream in, Quiz quiz);
}
