package ru.otus.HW181.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW181.domain.Book;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {
    Book findByTitle(String title);
}
