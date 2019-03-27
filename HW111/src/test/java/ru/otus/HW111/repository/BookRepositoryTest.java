package ru.otus.HW111.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.HW111.domain.Author;
import ru.otus.HW111.domain.Book;
import ru.otus.HW111.domain.Genre;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    private BookRepository bookRepository;

    private Book[] books;

    @Before
    public void cleanTheBase() {
        bookRepository.deleteAll().block();

        books = new Book[3];
        String genre1 = "Novel";
        String genre2 = "Poem";
        String author1 ="Pushkin";
        String author2 ="Tolstoy";

        books[0] = new Book("Peace And War");
        books[0].addGenre(new Genre(genre1));
        books[0].addAuthor(new Author(author1));

        books[1] = new Book("Ruslan and Ludmila");
        books[1].addGenre(new Genre(genre2));
        books[1].addAuthor(new Author(author2));


        books[2] = new Book("TestBook");
        books[2].addGenre(new Genre(genre1));
        books[2].addGenre(new Genre(genre2));
        books[2].addAuthor(new Author(author1));
        books[2].addAuthor(new Author(author2));

        for(Book book : books) {
            reactiveMongoTemplate.save(Mono.just(book)).block();
        }
    }

    @Test
    public void shouldSetIdOnSave() {
        Mono<Book> bookMono = bookRepository.save(new Book("Peace and War"));

        StepVerifier
                .create(bookMono)
                .assertNext(book -> assertNotNull(book.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    public void findBooksByGenreTest() {
        Flux<Book> bookFlux = bookRepository.findBooksByGenre(new Genre("Poem"));

        StepVerifier
                .create(bookFlux)
                .expectNext(books[1], books[2])
                .expectComplete()
                .verify();
    }

    @Test
    public void findBooksByAuthorTest() {
        Flux<Book> bookFlux = bookRepository.findBooksByAuthor(new Author("Tolstoy"));

        StepVerifier
                .create(bookFlux)
                .expectNext(books[1], books[2])
                .expectComplete()
                .verify();
    }

    @Test
    public void findBooksByAuthorsAndGenresTest_1() {
        Flux<Book> bookFlux = bookRepository.findBooksByAuthorsAndGenres(
                Arrays.asList("Pushkin"),
                Arrays.asList()
        );

        StepVerifier
                .create(bookFlux)
                .expectNext(books[0], books[2])
                .expectComplete()
                .verify();
    }

    @Test
    public void findBooksByAuthorsAndGenresTest_2() {
        Flux<Book> bookFlux = bookRepository.findBooksByAuthorsAndGenres(
                Arrays.asList("Pushkin", "Tolstoy"),
                Arrays.asList("Novel")
        );

        StepVerifier
                .create(bookFlux)
                .expectNext(books[2])
                .expectComplete()
                .verify();
    }
}
