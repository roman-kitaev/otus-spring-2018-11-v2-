package ru.otus.HW171.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW171.domain.Author;

import java.util.Collection;
import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Author findByName(String name);
    List<Author> findByNameIn(Collection names);
}