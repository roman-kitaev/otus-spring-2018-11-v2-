package ru.otus.HW041.service;

import ru.otus.HW041.data.Quiz;

import java.io.InputStream;

public interface Questioner {
    void ask(InputStream in, Quiz quiz);
}
