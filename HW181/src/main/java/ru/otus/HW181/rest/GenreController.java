package ru.otus.HW181.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.HW181.domain.Genre;
import ru.otus.HW181.service.GenreService;

import java.util.List;

@RestController
public class GenreController {
    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/api/genres")
    public List<Genre> getAllGenres() {
        return genreService.findAll();
    }
}
