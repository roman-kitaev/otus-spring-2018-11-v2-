package ru.otus.HW111.rest;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import ru.otus.HW111.domain.Author;
import ru.otus.HW111.domain.Book;
import ru.otus.HW111.domain.Genre;
import ru.otus.HW111.service.AuthorService;
import ru.otus.HW111.service.BookService;
import ru.otus.HW111.service.GenreService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
public class BookHandler {
    private BookService bookService;
    private AuthorService authorService;
    private GenreService genreService;

    public BookHandler(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    public Mono<ServerResponse> list(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON).body(bookService.findAll(), Book.class);
    }

    public Mono<ServerResponse> createBook(ServerRequest request) {
        return request.bodyToMono(Book.class).doOnNext(book ->
        {
            Set<String> authorsToCheck = new HashSet<>(book.getAuthors());
            Set<String> genresToCheck = new HashSet<>(book.getGenres());

            book.setAuthors(new HashSet<>());
            book.setGenres(new HashSet<>());

            Mono<List<Author>> monoAuthors =
                    authorService.findByNameIn(authorsToCheck).collectList();

            Mono<List<Genre>> monoGenres =
                    genreService.findByGenreIn(genresToCheck).collectList();

            Mono.zip(monoAuthors, monoGenres, new BiFunction<List<Author>, List<Genre>, Object>() {
                        @Override
                        public Object apply(List<Author> authors, List<Genre> genres) {
                            for(Author author : authors) book.addAuthor(author);
                            for(Genre genre : genres) book.addGenre(genre);
                            return new Object();
                        }
                    }
            ).then(bookService.save(book)).subscribe();
        }).then(ok().build());
    }

    public Mono<ServerResponse> findByTitle(ServerRequest request) {
        return ok().contentType(APPLICATION_JSON).
                body(bookService.findByTitle(request.queryParam("title").get()), Book.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {
        return bookService.findById(request.pathVariable("id")).
                flatMap(x -> ok().contentType(APPLICATION_JSON).body(fromObject(x)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> addComment(ServerRequest request) {
        return bookService.findById(request.pathVariable("id")).doOnNext(x ->
                request.bodyToMono(String.class).doOnNext(c -> x.addComment(c))
                        .then(bookService.save(x)).subscribe()
        ).then(ok().build());
    }

    public Mono<ServerResponse> deleteById(ServerRequest request) {
        return bookService.deleteById(request.queryParam("id").get()).then(ok().build());
    }
}
