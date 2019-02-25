package ru.otus.HW041.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.HW041.service.Display;
import ru.otus.HW041.service.NameRequester;
import ru.otus.HW041.service.Questioner;
import ru.otus.HW041.service.QuestionsLoader;
import ru.otus.HW041.data.Quiz;
import ru.otus.HW041.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
    private NameRequester nameRequester;
    private QuestionsLoader questionsLoader;
    private Questioner questioner;
    private Display display;

    @Autowired
    public QuizServiceImpl(NameRequester nameRequester,
                           QuestionsLoader questionsLoader,
                           Questioner questioner,
                           Display display) {
        this.nameRequester = nameRequester;
        this.questionsLoader = questionsLoader;
        this.questioner = questioner;
        this.display = display;
    }

    public void runQuiz() {
        String currentUser = nameRequester.askName(System.in);

        Quiz quiz = new Quiz(currentUser,
                questionsLoader.makeQuiz());

        questioner.ask(System.in, quiz);

        display.displayResults(currentUser, quiz.getQuiz(), quiz.getResult());
    }
}
