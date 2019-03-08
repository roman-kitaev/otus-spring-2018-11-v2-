package ru.otus.HW101.repostory;

import ru.otus.HW101.domain.Author;

import java.util.List;

public interface AuthorRepositoryCustom {
    List<Author> findAuthorsByNames(List<String> names);
}
