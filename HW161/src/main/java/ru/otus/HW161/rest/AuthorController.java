package ru.otus.HW161.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.HW161.domain.Author;
import ru.otus.HW161.service.AuthorService;

import java.util.List;

@RestController
public class AuthorController {
    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/api/authors")
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }
}
