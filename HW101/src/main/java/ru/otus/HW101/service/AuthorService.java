package ru.otus.HW101.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.HW101.domain.Author;
import ru.otus.HW101.repostory.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findByName(String name) {
        return authorRepository.findByName(name);
    }

    public List<Author> findAuthorsByNames(List<String> names) {
        return authorRepository.findAuthorsByNames(names);
    }
}
