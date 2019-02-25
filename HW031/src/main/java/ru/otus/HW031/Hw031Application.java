package ru.otus.HW031;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.HW031.quizservice.QuizService;

@SpringBootApplication
public class Hw031Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Hw031Application.class, args);
		QuizService quizService =
				context.getBean(QuizService.class);
		quizService.runQuiz();
	}
}

