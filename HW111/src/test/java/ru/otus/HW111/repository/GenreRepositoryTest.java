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
import ru.otus.HW111.domain.Genre;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenreRepositoryTest {

    @Autowired
    ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    private GenreRepository genreRepository;

    @Before
    public void cleanTheBase() {
        genreRepository.deleteAll().block();
    }

    @Test
    public void shouldSetIdOnSave() {
        Mono<Genre> genreMono = genreRepository.save(new Genre("Roman"));

        StepVerifier
                .create(genreMono)
                .assertNext(genre -> assertNotNull(genre.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    public void shouldFindByGenre() {
        String genre = "Roman";

        reactiveMongoTemplate.save(new Genre(genre)).block();

        Mono<Genre> foundMono = genreRepository.findByGenre(genre);

        StepVerifier
                .create(foundMono)
                .expectNext(new Genre(genre))
                .verifyComplete();
    }

    @Test
    public void shouldFindByGenreInCollection() {
        String[] genres = {"Roman", "Lyric", "Prose"};
        for(String genre : genres) {
            reactiveMongoTemplate.save(new Genre(genre)).block();
        }

        Set<String> genresToCheck = new HashSet<>();
        genresToCheck.add("Lyric");
        genresToCheck.add("BlaBlaGenre");

        Flux<Genre> filtered = genreRepository.findByGenreIn(genresToCheck);

        StepVerifier
                .create(filtered)
                .expectNextMatches(genre -> genre.getGenre().equals("Lyric"))
                .expectComplete()
                .verify();
    }
}
