package ru.otus.HW181.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.HW181.domain.Author;
import ru.otus.HW181.domain.Book;
import ru.otus.HW181.domain.Genre;
import ru.otus.HW181.rest.dto.BookDto;
import ru.otus.HW181.service.AuthorService;
import ru.otus.HW181.service.BookService;
import ru.otus.HW181.service.GenreService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private BookService bookService;
    private AuthorService authorService;
    private GenreService genreService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @HystrixCommand(fallbackMethod = "getDefaultBooks", groupKey = "BookService", commandKey = "findAll",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
            })
    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return bookService.findAll().stream().map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    public List<BookDto> getDefaultBooks() {
        Book defaultBook = new Book("Harry Potter and the Philosopher's Stone");
        defaultBook.addAuthor(new Author("J. K. Rowling"));
        defaultBook.addGenre(new Genre("fantasy"));
        defaultBook.addGenre(new Genre("drama"));
        defaultBook.addGenre(new Genre("young adult fiction"));
        defaultBook.addGenre(new Genre("mystery"));
        defaultBook.addGenre(new Genre("thriller"));
        defaultBook.addComment("Purely amazing.");
        defaultBook.addComment("It was good.");
        defaultBook.addComment("Just perfect.");
        defaultBook.addComment("It is definitely the best book I've ever read! " +
                "I need to read all!!!! :) SO AMAZING! " +
                "I now know 100% understand why J.K. Rowling is the most successful " +
                "and richest author there is! 9.9 STARS!");

        return Collections.singletonList(BookDto.toDto(defaultBook));
    }

    @GetMapping("/api/book/{idToGet}")
    public Book getBook(@PathVariable String idToGet) {
        String id = idToGet.replace("=", "");
        Book book = bookService.findById(id);
        return book;
    }

    @PostMapping("/api/addbook")
    public BookDto addBook(@RequestBody BookDto bookFromFront) {
        return addBookMethod(bookFromFront);
    }

    @PostMapping("/api/editbook")
    public BookDto editBook(@RequestBody BookDto bookFromFront) {
        return addBookMethod(bookFromFront);
    }

    @DeleteMapping("/api/delete/{idToDelete}")
    public Msg delete(@PathVariable String idToDelete) {
        String id = idToDelete.replace("=", "");
        bookService.deleteById(id);
        return Msg.getSuccess();
    }

    private BookDto addBookMethod(BookDto bookFromFront) {
        Book book = new Book(bookFromFront.getTitle());
        book.setId(bookFromFront.getId());
        book.setComments(bookFromFront.getComments());

        book.setAuthors(
                authorService.findByNameIn(bookFromFront.getAuthors())
                        .stream().map(author -> author.getName()).collect(Collectors.toSet()));

        book.setGenres(
                genreService.findByGenreIn(bookFromFront.getGenres())
                        .stream().map(genre -> genre.getGenre()).collect(Collectors.toSet()));

        book = bookService.save(book);

        BookDto bookDto = BookDto.toDto(book);
        bookDto.setMsgSuccess();

        return bookDto;
    }
}

