package ru.otus.HW081.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW081.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, Integer> {
}
