package ru.otus.HW051.dao;

import ru.otus.HW051.domain.Book;

import java.util.List;

public interface BookDao {
    void dropTable();
    void initiateTable();
    void insert(Book book);
    void insertBooks(List<Book> bookList);
    int count();
    Book getById(int id);
    Book getByTitle(String title);
    void delete(int id);
    List<Book> getAll();
}
