package ru.otus.HW061.repository;

import ru.otus.HW061.domain.Author;

import java.util.List;

public interface AuthorRepository extends MyRepository<Author>{
    void insert(Author author);
    Author getById(int id);
    Author getByName(String name);
    List<Author> getAll();
    long count();
    void drop();
}
