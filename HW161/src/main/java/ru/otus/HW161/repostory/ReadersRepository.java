package ru.otus.HW161.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW161.domain.Reader;

public interface ReadersRepository extends MongoRepository<Reader, String> {
    Reader findByUsername(String username);
}
