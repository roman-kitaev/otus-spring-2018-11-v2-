package ru.otus.HW101.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW101.domain.Book;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {
    Book findByTitle(String title);
}
