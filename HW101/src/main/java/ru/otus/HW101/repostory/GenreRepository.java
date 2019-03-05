package ru.otus.HW101.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW101.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
