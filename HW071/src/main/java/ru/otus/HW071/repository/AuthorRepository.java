package ru.otus.HW071.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.HW071.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findByName(String name);
}
