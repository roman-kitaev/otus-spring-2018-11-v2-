package ru.otus.HW111;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.HW111.domain.Author;
import ru.otus.HW111.domain.Book;
import ru.otus.HW111.domain.Genre;
import ru.otus.HW111.service.AuthorService;
import ru.otus.HW111.service.BookService;
import ru.otus.HW111.service.GenreService;

import java.util.*;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

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

	@Bean
	public RouterFunction<ServerResponse> composedRoutes(AuthorService authorService,
	GenreService genreService, BookService bookService) {

		AuthorHandler authorHandler = new AuthorHandler(authorService);
		GenreHandler genreHandler = new GenreHandler(genreService);
		BookHandler bookHandler = new BookHandler(bookService, authorService, genreService);

		RouterFunction<ServerResponse> route = route()
				.GET("/func/authors", accept(APPLICATION_JSON), authorHandler::list)
				.POST("/func/authors/create", accept(APPLICATION_JSON), authorHandler::createAuthor)

				.GET("/func/genres", accept(APPLICATION_JSON), genreHandler::list)
				.POST("/func/genres/create", accept(APPLICATION_JSON), genreHandler::createGenre)

				.GET("/func/books", accept(APPLICATION_JSON), bookHandler::list)
				.POST("/func/books/create", accept(APPLICATION_JSON), bookHandler::createBook)
				.GET("/func/books/title", accept(APPLICATION_JSON), bookHandler::findByTitle)
				.GET("/func/books/{id}", accept(APPLICATION_JSON), bookHandler::findById)
				.POST("/func/books/{id}/addcomment", accept(TEXT_PLAIN), bookHandler::addComment)
				.DELETE("/func/books/deletebyid", accept(APPLICATION_JSON), bookHandler::deleteById)
				.build();

		return route;
	}

	static class AuthorHandler {

		private AuthorService authorService;

		AuthorHandler(AuthorService authorService) {
			this.authorService = authorService;
		}

		Mono<ServerResponse> list(ServerRequest request) {
			return ok().contentType(APPLICATION_JSON).body(authorService.findAll(), Author.class);
		}

		Mono<ServerResponse> createAuthor(ServerRequest request) {
			Mono<Author> author = request.bodyToMono(Author.class);
			return author.doOnNext(x -> authorService.save(x).subscribe()).
					then(ok().build());
		}
	}

	static class GenreHandler {

		private GenreService genreService;

		public GenreHandler(GenreService genreService) {
			this.genreService = genreService;
		}

		Mono<ServerResponse> list(ServerRequest request) {
			return ok().contentType(APPLICATION_JSON).body(genreService.findAll(), Genre.class);
		}

		Mono<ServerResponse> createGenre(ServerRequest request) {
			Mono<Genre> genre = request.bodyToMono(Genre.class);
			return genre.doOnNext(x -> genreService.save(x).subscribe()).
					then(ok().build());
		}
	}

	static class BookHandler {

		private BookService bookService;
		private AuthorService authorService;
		private GenreService genreService;

		public BookHandler(BookService bookService, AuthorService authorService, GenreService genreService) {
			this.bookService = bookService;
			this.authorService = authorService;
			this.genreService = genreService;
		}

		Mono<ServerResponse> list(ServerRequest request) {
			return ok().contentType(APPLICATION_JSON).body(bookService.findAll(), Book.class);
		}

		Mono<ServerResponse> createBook(ServerRequest request) {
			return request.bodyToMono(Book.class).doOnNext(book ->
			{
				Set<String> authorsToCheck = new HashSet<>(book.getAuthors());
				Set<String> genresToCheck = new HashSet<>(book.getGenres());

				book.setAuthors(new HashSet<>());
				book.setGenres(new HashSet<>());

				authorService.findByNameIn(authorsToCheck).doOnNext(x ->
					book.addAuthor(x)).subscribe();
				genreService.findByGenreIn(genresToCheck).doOnNext(x ->
					book.addGenre(x)).then(bookService.save(book)).subscribe();

			}).then(ok().build());
		}

		Mono<ServerResponse> findByTitle(ServerRequest request) {
			return ok().contentType(APPLICATION_JSON).
					body(bookService.findByTitle(request.queryParam("title").get()), Book.class);
		}

		Mono<ServerResponse> findById(ServerRequest request) {
			return bookService.findById(request.pathVariable("id")).
					flatMap(x -> ok().contentType(APPLICATION_JSON).body(fromObject(x)))
					.switchIfEmpty(ServerResponse.notFound().build());
		}

		Mono<ServerResponse> addComment(ServerRequest request) {
				return bookService.findById(request.pathVariable("id")).doOnNext(x ->
				request.bodyToMono(String.class).doOnNext(c -> x.addComment(c))
						.then(bookService.save(x)).subscribe()
			).then(ok().build());
		}

		Mono<ServerResponse> deleteById(ServerRequest request) {
			return bookService.deleteById(request.queryParam("id").get()).then(ok().build());
		}
	}
}
