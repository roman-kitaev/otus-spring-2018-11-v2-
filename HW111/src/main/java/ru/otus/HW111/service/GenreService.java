package ru.otus.HW111.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.HW111.domain.Genre;
import ru.otus.HW111.repository.GenreRepository;

import java.util.Collection;


@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public Mono<Genre> save(Genre genre) {
        return genreRepository.save(genre);
    }

    public Flux<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Mono<Genre> findByGenre(String genre) {
        return genreRepository.findByGenre(genre);
    }

    public Flux<Genre> findByGenreIn(Collection genres) {
        return genreRepository.findByGenreIn(genres);
    }
}
