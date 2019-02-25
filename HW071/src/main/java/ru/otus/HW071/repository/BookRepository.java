package ru.otus.HW071.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.HW071.domain.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Book findByTitle(String title);
}
