package ru.otus.HW161.repostory;

import ru.otus.HW161.domain.Author;
import ru.otus.HW161.domain.Book;
import ru.otus.HW161.domain.Genre;

import java.util.List;

public interface BookRepositoryCustom {
    void addComment(Book book, String comment);
    List<Book> findBooksByGenre(Genre genre);
    List<Book> findBooksByAuthor(Author author);
    List<Book> findBooksByAuthorsAndGenres(List<String> authors,
                                           List<String> genres);
}
