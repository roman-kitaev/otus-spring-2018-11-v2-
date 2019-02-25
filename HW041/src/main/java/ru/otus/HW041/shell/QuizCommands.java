package ru.otus.HW041.shell;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.HW041.service.LocalisationService;
import ru.otus.HW041.service.QuizService;

@ShellComponent
public class QuizCommands {
    @Autowired
    QuizService quizService;

    @Autowired
    private LocalisationService localisationService;

    @ShellMethod("Starts the quiz")
    public void start() {
        quizService.runQuiz();
    }

    @ShellMethod("Change the locale")
    public String setlocale(@ShellOption String language) {
        localisationService.setLocale(language);
        return "your locale has been changed to " + language;
    }
}
