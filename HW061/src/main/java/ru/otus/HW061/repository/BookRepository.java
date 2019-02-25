package ru.otus.HW061.repository;

import ru.otus.HW061.domain.Book;

import java.util.List;

public interface BookRepository{
    void insert(Book book);
    void insertBooks(List<Book> bookList);
    Book getById(int id);
    Book getByTitle(String title);
    List<Book> getAll();
    long count();
    void delete(int id);
    void drop();
}
