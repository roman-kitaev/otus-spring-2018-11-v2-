package ru.otus.HW101.repostory;

import ru.otus.HW101.domain.Genre;

import java.util.List;

public interface GenreRepositoryCustom {
    List<Genre> findGenresByNames(List<String> names);
}
