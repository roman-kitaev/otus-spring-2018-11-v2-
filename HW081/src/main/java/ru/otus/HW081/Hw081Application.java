package ru.otus.HW081;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.HW081.domain.Author;
import ru.otus.HW081.domain.Book;
import ru.otus.HW081.domain.Genre;
import ru.otus.HW081.repostory.AuthorRepository;
import ru.otus.HW081.repostory.BookRepository;
import ru.otus.HW081.repostory.GenreRepository;

import java.util.Arrays;

@SpringBootApplication
public class Hw081Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Hw081Application.class);

		AuthorRepository authorRepository = context.getBean(AuthorRepository.class);
		GenreRepository genreRepository = context.getBean(GenreRepository.class);
		BookRepository bookRepository = context.getBean(BookRepository.class);

		/*authorRepository.save(new Author("Pushkin"));
		authorRepository.save(new Author("Tolstoy"));
		genreRepository.save(new Genre("Prose"));
		genreRepository.save(new Genre("Roman"));
		genreRepository.save(new Genre("Lyric"));

		System.out.println(authorRepository.findAll());
		System.out.println(genreRepository.findAll());

		Book book = new Book("BookTitle1");
		book.addAuthor(new Author("Tolstoy"));
		book.addAuthor(new Author("Pushkin"));
		book.addGenre(new Genre("Prose"));

		Book book2 = new Book("BookTitle2");
		book2.addAuthor(new Author("Tolstoy"));
		book2.addAuthor(new Author("Pushkin"));
		book2.addGenre(new Genre("Prose"));

		Book book3 = new Book("BookTitle3");
		book3.addAuthor(new Author("Tolstoy"));
		book3.addAuthor(new Author("Pushkin"));
		book3.addGenre(new Genre("Lyric"));

        Book book4 = new Book("BookTitle4");
        book4.addAuthor(new Author("Tolstoy"));
        book4.addGenre(new Genre("Lyric"));

		bookRepository.saveAll(Arrays.asList(book, book2, book3, book4));*/

		/*Book book = bookRepository.findByTitle("Book1");

		bookRepository.addComment(book, "SUPER UBER COMMENT 333333");*/

		/*System.out.println(bookRepository.findBooksByGenre("Lyric"));

		Book book4 = new Book("BookTitle4");
		book4.addAuthor("Tolstoy");
		book4.addAuthor("Pushkin");
		book4.addGenre("Lyric");
		book4.addGenre("Prose");
		bookRepository.save(book4);

		System.out.println(bookRepository.
				findBooksByGenres(new String[]{"Prose", "Lyric"}));*/

/*		Book book = bookRepository.findByTitle("BookTitle1");
		book.addComment("BLABLABLA");
		book.addComment("BLOBLOBLO");
		bookRepository.save(book);
		bookRepository.addComment(book, "BLEBLEBLE");*/

		/*bookRepository.findBooksByAuthorsAndGenres(
			Arrays.asList("Pushkin", "Tolstoy"),
			Arrays.asList("Prose")
		).forEach(System.out::println);*/

		bookRepository.findBooksByGenre(new Genre("Prose")).forEach(System.out::println);

	}

}

