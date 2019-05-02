package ru.otus.HW131.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW131.domain.Author;

import java.util.Collection;
import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Author findByName(String name);
    List<Author> findByNameIn(Collection names);
}