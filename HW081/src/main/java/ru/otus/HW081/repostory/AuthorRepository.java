package ru.otus.HW081.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW081.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, Integer> {

}
