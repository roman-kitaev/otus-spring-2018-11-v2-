package ru.otus.HW091.repostory;

import ru.otus.HW091.domain.Author;
import ru.otus.HW091.domain.Book;
import ru.otus.HW091.domain.Genre;

import java.util.List;

public interface BookRepositoryCustom {
    void addComment(Book book, String comment);
    List<Book> findBooksByAuthorsAndGenres(List<String> authors,
                                           List<String> genres);
    List<Book> findBooksByGenre(Genre genre);

    List<Book> findBooksByAuthor(Author author);
}
