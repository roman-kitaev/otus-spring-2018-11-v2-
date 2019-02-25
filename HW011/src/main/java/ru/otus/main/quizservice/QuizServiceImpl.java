package ru.otus.main.quizservice;

import ru.otus.main.display.Display;
import ru.otus.main.namerequester.NameRequester;
import ru.otus.main.questioner.Questioner;
import ru.otus.main.quiz.Quiz;
import ru.otus.main.quiz.Quizer;

public class QuizServiceImpl implements QuizService{
    private NameRequester nameRequester;
    private Quizer quizer;
    private Questioner questioner;
    private Display display;

    public QuizServiceImpl(NameRequester nameRequester,
                       Quizer quizer,
                       Questioner questioner,
                       Display display) {
        this.nameRequester = nameRequester;
        this.quizer = quizer;
        this.questioner = questioner;
        this.display = display;
    }

    public void runQuiz() {
        Quiz quiz = quizer.makeQuiz();

        String currentUser = nameRequester.askName(System.in);
        System.out.println("Thank you, " + currentUser);

        boolean[] result = questioner.ask(System.in, quiz);
        display.displayResults(result, quiz);
    }
}
