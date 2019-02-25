package ru.otus.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.main.quizservice.QuizService;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizService quizService = (QuizService) context.getBean("quizservice");
        quizService.runQuiz();
    }
}
