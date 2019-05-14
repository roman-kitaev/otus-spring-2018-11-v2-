package ru.otus.HW171.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW171.domain.Reader;

public interface ReadersRepository extends MongoRepository<Reader, String> {
    Reader findByUsername(String username);
}
