package ru.otus.HW101.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW101.domain.Author;

import java.util.Collection;
import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Author findByName(String name);
    List<Author> findByNameIn(Collection names);
}