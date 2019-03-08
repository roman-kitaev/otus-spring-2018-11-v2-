package ru.otus.HW101;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.HW101.domain.Author;
import ru.otus.HW101.domain.Book;
import ru.otus.HW101.domain.Genre;
import ru.otus.HW101.repostory.AuthorRepository;
import ru.otus.HW101.repostory.BookRepository;
import ru.otus.HW101.repostory.GenreRepository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;


@SpringBootApplication
public class Hw101Application {

	public static void main(String[] args) {
		SpringApplication.run(Hw101Application.class, args);
	}

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private BookRepository bookRepository;

	@PostConstruct
	public void init() {
		/*Author author = new Author("Pushkin");
		authorRepository.save(author);
		authorRepository.save(new Author("Lermontov"));
		authorRepository.save(new Author("Tolstoy"));
		authorRepository.save(new Author("Necrasov"));

		Genre genre = new Genre("Poem");
		genreRepository.save(genre);
		genreRepository.save(new Genre("Roman"));
		genreRepository.save(new Genre("Prose"));
		genreRepository.save(new Genre("Lyric"));

		Book book = new Book("Ruslan and Ludmila");
		Set<String> authors = new HashSet<>();
		authors.add(author.getName());

		Set<String> genres = new HashSet<>();
		genres.add(genre.getGenre());

		book.setAuthors(authors);
		book.setGenres(genres);

		bookRepository.save(book);*/
	}
}
