package ru.otus.HW161.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.HW161.domain.Book;
import ru.otus.HW161.rest.dto.BookDto;
import ru.otus.HW161.service.AuthorService;
import ru.otus.HW161.service.BookService;
import ru.otus.HW161.service.GenreService;

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

