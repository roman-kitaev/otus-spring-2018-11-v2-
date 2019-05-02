package ru.otus.HW141;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.HW141.domain.Book;
import ru.otus.HW141.repostory.BookRepository;

@SpringBootApplication
public class Hw141Application {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Hw141Application.class, args);

/*  		BookRepository bookRepository = context.getBean(BookRepository.class);

		for(int i = 0; i < 10; i++) {
			bookRepository.save(new Book("Boook" + (i + 1)));
		}*/
	}
}
