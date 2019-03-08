package ru.otus.HW101.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.HW101.domain.Genre;
import ru.otus.HW101.repostory.GenreRepository;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre findByGenre(String genre) {
        return genreRepository.findByGenre(genre);
    }

    public List<Genre> findGenresByNames(List<String> names) {
        return genreRepository.findGenresByNames(names);
    }
}
