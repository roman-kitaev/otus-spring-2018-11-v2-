package ru.otus.HW081.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW081.domain.Book;

public interface BookRepository extends MongoRepository<Book, Integer>, BookRepositoryCustom {
    Book findByTitle(String title);
}
