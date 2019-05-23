package ru.otus.HW181.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.HW181.NotFoundException;
import ru.otus.HW181.domain.Author;
import ru.otus.HW181.domain.Book;
import ru.otus.HW181.domain.Genre;
import ru.otus.HW181.repostory.BookRepository;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(String id) {
        return bookRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(String id) {
        bookRepository.deleteById(id);
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> findBooksByAuthorsOrGenres(List<String> authors,
                                                 List<String> genres) {
        return bookRepository.findBooksByAuthorsAndGenres(authors, genres);
    }

    public void addComment(Book book, String comment) {
        bookRepository.addComment(book, comment);
    }

    public List<Book> findBooksByGenre(Genre genre) {
        return bookRepository.findBooksByGenre(genre);
    }

    public List<Book> findBooksByAuthor(Author author) {
        return bookRepository.findBooksByAuthor(author);
    }
}
