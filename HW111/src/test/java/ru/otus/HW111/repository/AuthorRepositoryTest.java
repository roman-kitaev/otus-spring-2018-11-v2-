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

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    private AuthorRepository authorRepository;

    @Before
    public void cleanTheBase() {
        authorRepository.deleteAll().block();
    }

    @Test
    public void shouldSetIdOnSave() {
        Mono<Author> authorMono = authorRepository.save(new Author("Pushkin"));

        StepVerifier
                .create(authorMono)
                .assertNext(author -> assertNotNull(author.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    public void shouldFindByName() {
        String name = "Pushkin";

        reactiveMongoTemplate.save(new Author(name)).block();

        Mono<Author> foundMono = authorRepository.findByName(name);

        StepVerifier
                .create(foundMono)
                .expectNext(new Author(name))
                .verifyComplete();
    }

    @Test
    public void shouldFindByNameInCollection() {
        String[] names = {"Pushkin", "Lermontov", "Tolstoy"};
        for(String name : names) {
            reactiveMongoTemplate.save(new Author(name)).block();
        }

        Set<String> authorsToCheck = new HashSet<>();
        authorsToCheck.add("Pushkin");

        Flux<Author> filtered = authorRepository.findByNameIn(authorsToCheck);

        StepVerifier
                .create(filtered)
                .expectNextMatches(author -> author.getName().equals("Pushkin"))
                .expectComplete()
                .verify();
    }
}
