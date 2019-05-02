package ru.otus.HW141.repostory;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.HW141.domain.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
