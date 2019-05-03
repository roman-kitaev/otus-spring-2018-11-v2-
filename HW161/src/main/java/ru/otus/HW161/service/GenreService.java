package ru.otus.HW161.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.HW161.domain.Genre;
import ru.otus.HW161.repostory.GenreRepository;

import java.util.Collection;
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

    public List<Genre> findByGenreIn(Collection genres) {
        return genreRepository.findByGenreIn(genres);
    }
}
