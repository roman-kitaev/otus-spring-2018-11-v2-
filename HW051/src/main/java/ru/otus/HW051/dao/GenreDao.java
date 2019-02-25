package ru.otus.HW051.dao;

import ru.otus.HW051.domain.Genre;

import java.util.List;

public interface GenreDao {
    void initiateTable();
    void insert(Genre genre);
    void insertGenres(List<Genre> genreList);
    int count();
    Genre getByGenre(String genre);
    void delete(String genre);
    List<Genre> getAll();
}
