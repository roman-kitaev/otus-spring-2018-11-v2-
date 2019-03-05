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
}
