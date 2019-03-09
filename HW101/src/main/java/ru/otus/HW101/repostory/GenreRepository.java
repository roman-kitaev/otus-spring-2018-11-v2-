package ru.otus.HW101.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW101.domain.Genre;

import java.util.Collection;
import java.util.List;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Genre findByGenre(String genre);
    List<Genre> findByGenreIn(Collection genres);
}
