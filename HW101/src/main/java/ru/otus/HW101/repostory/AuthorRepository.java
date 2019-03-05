package ru.otus.HW101.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW101.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
