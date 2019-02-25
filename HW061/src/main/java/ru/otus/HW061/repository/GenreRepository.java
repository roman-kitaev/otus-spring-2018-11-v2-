package ru.otus.HW061.repository;

import ru.otus.HW061.domain.Genre;

import java.util.List;

public interface GenreRepository extends MyRepository<Genre>{
    void insert(Genre genre);
    Genre getByTitle(String title);
    List<Genre> getAll();
    long count();
    void drop();
}
