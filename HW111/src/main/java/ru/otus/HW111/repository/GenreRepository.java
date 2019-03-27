package ru.otus.HW111.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.HW111.domain.Genre;

import java.util.Collection;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {
    Mono<Genre> findByGenre(String genre);
    Flux<Genre> findByGenreIn(Collection genre);
}
