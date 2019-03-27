package ru.otus.HW111.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.HW111.domain.Author;
import ru.otus.HW111.domain.Book;
import ru.otus.HW111.domain.Genre;

import java.util.List;

public interface BookRepositoryCustom {
    Mono<Void> addComment(Book book, String comment);
    Flux<Book> findBooksByGenre(Genre genre);
    Flux<Book> findBooksByAuthor(Author author);
    Flux<Book> findBooksByAuthorsAndGenres(List<String> authors,
                                           List<String> genres);
}
