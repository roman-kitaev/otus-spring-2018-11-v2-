package ru.otus.HW101.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.HW101.domain.Book;
import ru.otus.HW101.rest.dto.BookDto;
import ru.otus.HW101.service.AuthorService;
import ru.otus.HW101.service.BookService;
import ru.otus.HW101.service.GenreService;

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

    @GetMapping("/api/books")
    public List<BookDto> getAllBooks() {
        return bookService.findAll().stream().map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/book/{idToGet}")
    public Book getBook(@PathVariable String idToGet) {
        String id = idToGet.replace("=", "");
        Book book = bookService.findById(id);
        return book;
    }

    @PostMapping("/api/addbook")
    public BookDto addBook(@RequestBody Book bookToAdd) {
        List<String> availableAuthors = authorService.findAll().stream().map(author -> author.getName()).collect(Collectors.toList());
        bookToAdd.getAuthors().retainAll(availableAuthors);

        List<String> availableGenres = genreService.findAll().stream().map(genre -> genre.getGenre()).collect(Collectors.toList());
        bookToAdd.getGenres().retainAll(availableGenres);

        bookToAdd = bookService.save(bookToAdd);

        BookDto bookDto = BookDto.toDto(bookToAdd);
        bookDto.setMsgSuccess();

        return bookDto;
    }

    @PostMapping("/api/delbook")
    public String delBook(@RequestBody String idToDelete) {
        String id = idToDelete.replace("=", "");
        bookService.deleteById(id);
        return "{\"msg\":\"success\"}";
    }
}
