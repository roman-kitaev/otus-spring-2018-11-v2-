package ru.otus.HW091.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.HW091.domain.Author;
import ru.otus.HW091.domain.Book;
import ru.otus.HW091.domain.Genre;
import ru.otus.HW091.repostory.AuthorRepository;
import ru.otus.HW091.repostory.BookRepository;
import ru.otus.HW091.repostory.GenreRepository;

import java.util.List;

@Controller
public class BooksController {
    AuthorRepository authorRepository;
    BookRepository bookRepository;
    GenreRepository genreRepository;

    @Autowired
    public BooksController(AuthorRepository authorRepository,
                           BookRepository bookRepository,
                           GenreRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;

        authorRepository.save(new Author("Pushkin"));
        authorRepository.save(new Author("Tolstoy"));
        genreRepository.save(new Genre("Prose"));
        genreRepository.save(new Genre("Roman"));
        genreRepository.save(new Genre("Lyric"));
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") String id, Model model) {
        Book book = bookRepository.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "edit";
    }

    @GetMapping("/edited")
    public String editedPage(@RequestParam("id") String id, @RequestParam("newtitle") String newtitle,
                             @RequestParam("newgenres") String newgenres,
                             @RequestParam("newauthors") String newauthors,
                             @RequestParam("newcomments") String newcomments) {
        Book book = bookRepository.findById(id).orElseThrow(NotFoundException::new);

        book.setTitle(newtitle);
        book.setGenresFromWebInput(newgenres, genreRepository.findAll());
        book.setAuthorsFromWebInput(newauthors, authorRepository.findAll());
        book.setCommentsFromWebInput(newcomments);

        bookRepository.save(book);

        return "redirect:edit?id=" + book.getId();
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") String id) {
        bookRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String add(@RequestParam("title") String title,
                             @RequestParam("genres") String genres,
                             @RequestParam("authors") String authors) {
        Book book = new Book();

        book.setTitle(title);
        book.setGenresFromWebInput(genres, genreRepository.findAll());
        book.setAuthorsFromWebInput(authors, authorRepository.findAll());

        bookRepository.save(book);

        return "redirect:/";
    }
}
