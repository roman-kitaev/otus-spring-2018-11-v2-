package ru.otus.HW131;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.otus.HW131.domain.Reader;
import ru.otus.HW131.repostory.ReadersRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Hw131Application {

	public static void main(String[] args) {
		SpringApplication.run(Hw131Application.class, args);

		System.out.println("login: admin1");
		System.out.println("password: admin1\n");

		System.out.println("login: admin2");
		System.out.println("password: admin2\n");

		System.out.println("login: admin3");
		System.out.println("password: admin3");
	}

	/*@Autowired
	private ReadersRepository readersRepository;
	*@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private BookRepository bookRepository;

	@PostConstruct
	public void init() {
		Author author = new Author("Pushkin");
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

		bookRepository.save(book);

		Reader admin1 = new Reader("admin1",
				new BCryptPasswordEncoder().encode("admin1"));
		readersRepository.save(admin1);

		Reader admin2 = new Reader("admin2",
				new BCryptPasswordEncoder().encode("admin2"));
		readersRepository.save(admin2);

		Reader admin3 = new Reader("admin3",
				new BCryptPasswordEncoder().encode("admin3"));
		readersRepository.save(admin3);
	}*/

}
