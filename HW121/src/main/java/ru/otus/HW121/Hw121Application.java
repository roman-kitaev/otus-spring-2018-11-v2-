package ru.otus.HW121;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.otus.HW121.domain.Author;
import ru.otus.HW121.domain.Book;
import ru.otus.HW121.domain.Genre;
import ru.otus.HW121.domain.Reader;
import ru.otus.HW121.repostory.AuthorRepository;
import ru.otus.HW121.repostory.BookRepository;
import ru.otus.HW121.repostory.GenreRepository;
import ru.otus.HW121.repostory.ReadersRepository;
import ru.otus.HW121.rest.BookController;
import ru.otus.HW121.service.BookService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Hw121Application {
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ReadersRepository readersRepository;

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

		/*Reader admin = new Reader("admin", new BCryptPasswordEncoder().encode("admin"));
		readersRepository.save(admin);*/
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Hw121Application.class, args);

		System.out.println("login: admin");
		System.out.println("password: admin");
	}

}
