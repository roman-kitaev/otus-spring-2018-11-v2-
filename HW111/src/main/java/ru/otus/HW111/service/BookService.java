package ru.otus.HW111.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.HW111.domain.Author;
import ru.otus.HW111.domain.Book;
import ru.otus.HW111.domain.Genre;
import ru.otus.HW111.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Flux<Book> findAll() {
        return bookRepository.findAll();
    }

    public Mono<Book> findById(String id) {
        return bookRepository.findById(id);
    }

    public Mono<Book> save(Book book) {
        return bookRepository.save(book);
    }

    public Mono<Void> deleteById(String id) {
        return bookRepository.deleteById(id);
    }

    public Mono<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Flux<Book> findBooksByAuthorsOrGenres(List<String> authors,
                                                 List<String> genres) {
        return bookRepository.findBooksByAuthorsAndGenres(authors, genres);
    }

    public Mono<Void> addComment(Book book, String comment) {
        return bookRepository.addComment(book, comment);
    }

    public Flux<Book> findBooksByGenre(Genre genre) {
        return bookRepository.findBooksByGenre(genre);
    }

    public Flux<Book> findBooksByAuthor(Author author) {
        return bookRepository.findBooksByAuthor(author);
    }
}
