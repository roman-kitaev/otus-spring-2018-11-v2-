package ru.otus.HW121.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW121.domain.Reader;

public interface ReadersRepository extends MongoRepository<Reader, String> {
    Reader findByUsername(String username);
}
