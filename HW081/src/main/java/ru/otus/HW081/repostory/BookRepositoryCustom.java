package ru.otus.HW081.repostory;

import ru.otus.HW081.domain.Author;
import ru.otus.HW081.domain.Book;
import ru.otus.HW081.domain.Genre;

import java.util.List;

public interface BookRepositoryCustom {
    void addComment(Book book, String comment);
    List<Book> findBooksByAuthorsAndGenres(List<String> authors,
                                                List<String> genres);
    List<Book> findBooksByGenre(Genre genre);

    List<Book> findBooksByAuthor(Author author);
}
