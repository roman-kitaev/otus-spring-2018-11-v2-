package ru.otus.HW171.repostory;

import ru.otus.HW171.domain.Author;
import ru.otus.HW171.domain.Book;
import ru.otus.HW171.domain.Genre;

import java.util.List;

public interface BookRepositoryCustom {
    void addComment(Book book, String comment);
    List<Book> findBooksByGenre(Genre genre);
    List<Book> findBooksByAuthor(Author author);
    List<Book> findBooksByAuthorsAndGenres(List<String> authors,
                                           List<String> genres);
}
