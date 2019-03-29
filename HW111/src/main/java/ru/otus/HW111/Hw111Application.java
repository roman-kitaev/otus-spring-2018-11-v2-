package ru.otus.HW111;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.HW111.domain.Author;
import ru.otus.HW111.domain.Book;
import ru.otus.HW111.domain.Genre;
import ru.otus.HW111.service.AuthorService;
import ru.otus.HW111.service.BookService;
import ru.otus.HW111.service.GenreService;

@SpringBootApplication
public class Hw111Application {
	public static void main(String[] args) throws InterruptedException{
		ApplicationContext context =
		SpringApplication.run(Hw111Application.class, args);

		Author author1 = new Author("Pushkin");
		Author author2 = new Author("Tolstoy");
		Author author3 = new Author("Lermontov");
		Author author4 = new Author("Marinina");

		AuthorService authorService = context.getBean(AuthorService.class);
		authorService.save(author1).subscribe();
		authorService.save(author2).subscribe();
		authorService.save(author3).subscribe();
		authorService.save(author4).subscribe();

		Genre genre1 = new Genre("Prose");
		Genre genre2 = new Genre("Roman");
		Genre genre3 = new Genre("Lyric");
		Genre genre4 = new Genre("Epic");

		GenreService genreService = context.getBean(GenreService.class);
		genreService.save(genre1).subscribe();
		genreService.save(genre2).subscribe();
		genreService.save(genre3).subscribe();
		genreService.save(genre4).subscribe();

		Book book1 = new Book("Peace and war");
		book1.addAuthor(author2);
		book1.addAuthor(author3);
		book1.addGenre(genre2);
		Book book2 = new Book("Peace and war 2");
		book2.addAuthor(author3);
		book2.addGenre(genre1);
		book2.addGenre(genre2);

		BookService bookService = context.getBean(BookService.class);
		bookService.save(book1).subscribe();
		bookService.save(book2).subscribe();
	}
}
