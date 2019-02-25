package ru.otus.HW031.quizservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.HW031.display.Display;
import ru.otus.HW031.namerequester.NameRequester;
import ru.otus.HW031.questioner.Questioner;
import ru.otus.HW031.quiz.QuestionsLoader;
import ru.otus.HW031.quiz.Quiz;

@Service
public class QuizServiceImpl implements QuizService{
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
