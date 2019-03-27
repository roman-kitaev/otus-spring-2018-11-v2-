package ru.otus.HW111.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.HW111.domain.Author;
import ru.otus.HW111.repository.AuthorRepository;

import java.util.Collection;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Mono<Author> save(Author person) {
        return authorRepository.save(person);
    }

    public Flux<Author> findAll() {
        return authorRepository.findAll();
    }

    public Mono<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    public Flux<Author> findByNameIn(Collection names) {
        return authorRepository.findByNameIn(names);
    }
}
