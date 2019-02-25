package ru.otus.HW051.dao;

import ru.otus.HW051.domain.Author;

import java.util.List;

public interface AuthorDao {
    void initiateTable();
    void insert(Author author);
    void insertAuthors(List<Author> authorList);
    int count();
    Author getById(int id);
    void delete(int id);
    List<Author> getAll();
}
