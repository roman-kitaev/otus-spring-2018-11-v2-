package ru.otus.HW091.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW091.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
