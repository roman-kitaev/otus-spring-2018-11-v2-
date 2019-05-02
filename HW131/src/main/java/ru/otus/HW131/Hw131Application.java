package ru.otus.HW131;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.otus.HW131.domain.Reader;
import ru.otus.HW131.domain.Role;
import ru.otus.HW131.repostory.ReadersRepository;
import ru.otus.HW131.repostory.RoleRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;

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
	private AuthorRepository authorRepository;
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ReadersRepository readersRepository;*/

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

		/*Role role1 = new Role("role1",
				Arrays.asList("ROLE_CAN_DELETE", "ROLE_CAN_DO_SOMETHING_ELSE"));

		Role role2 = new Role("role2",
				Arrays.asList("ROLE_CAN_EDIT"));

		Role role3 = new Role("role3",
				Arrays.asList("ROLE_CAN_ADD"));

		Role role4 = new Role("role4",
				Arrays.asList("ROLE_CAN_TALK_WITH_DOGS"));

		roleRepository.saveAll(Arrays.asList(role1, role2, role3, role4));*/

		/*Reader admin1 = new Reader("admin1",
				new BCryptPasswordEncoder().encode("admin1"),
				Arrays.asList(roleRepository.findById("role1").get()));
		readersRepository.save(admin1);

		Reader admin2 = new Reader("admin2",
				new BCryptPasswordEncoder().encode("admin2"),
				Arrays.asList(roleRepository.findById("role2").get()));
		readersRepository.save(admin2);

		Reader admin3 = new Reader("admin3",
				new BCryptPasswordEncoder().encode("admin3"),
				Arrays.asList(roleRepository.findById("role3").get(),
						roleRepository.findById("role4").get()));
		readersRepository.save(admin3);*/
	}
}
