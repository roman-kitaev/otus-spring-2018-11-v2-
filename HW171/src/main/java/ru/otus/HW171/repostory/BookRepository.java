package ru.otus.HW171.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW171.domain.Book;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {
    Book findByTitle(String title);
}
