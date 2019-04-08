package ru.otus.HW121.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW121.domain.Book;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {
    Book findByTitle(String title);
}
