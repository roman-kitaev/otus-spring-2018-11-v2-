package ru.otus.HW091.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW091.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
