package ru.otus.HW071.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.HW071.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, String> {
}
