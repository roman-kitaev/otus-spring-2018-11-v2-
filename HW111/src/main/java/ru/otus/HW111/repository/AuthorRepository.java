package ru.otus.HW111.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.HW111.domain.Author;

import java.util.Collection;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
    Mono<Author> findByName(String name);
    Flux<Author> findByNameIn(Collection names);
}
