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
        bookToAdd.setAuthors(
        bookToAdd.getAuthors().stream().
                filter(author -> (authorService.findByName(author) != null) ? true : false).
                collect(Collectors.toSet()));

        bookToAdd.setGenres(
        bookToAdd.getGenres().stream().
                filter(genre -> (genreService.findByGenre(genre) != null) ? true : false).
                collect(Collectors.toSet()));

        bookToAdd = bookService.save(bookToAdd);

        BookDto bookDto = BookDto.toDto(bookToAdd);
        bookDto.setMsgSuccess();

        return bookDto;
    }

    @PostMapping("/api/delete/{idToDelete}")
    public String delete(@PathVariable String idToDelete) {
        String id = idToDelete.replace("=", "");
        bookService.deleteById(id);
        MsgStatusForAjax msgStatus = MsgStatusForAjax.getSuccessMsgStatus();
        return msgStatus.getStatus();
    }

    private static class MsgStatusForAjax {
        private String status;

        private MsgStatusForAjax(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public static MsgStatusForAjax getSuccessMsgStatus() {
            return new MsgStatusForAjax("{\"msg\":\"success\"}");
        }

        public static MsgStatusForAjax getErrorMsgStatus() {
            return new MsgStatusForAjax("{\"msg\":\"error\"}");
        }
    }
}

