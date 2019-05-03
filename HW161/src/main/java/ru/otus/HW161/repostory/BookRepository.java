package ru.otus.HW161.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW161.domain.Book;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {
    Book findByTitle(String title);
}
