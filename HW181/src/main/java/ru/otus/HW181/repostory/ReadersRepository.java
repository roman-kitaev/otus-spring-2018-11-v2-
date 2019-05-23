package ru.otus.HW181.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW181.domain.Reader;

public interface ReadersRepository extends MongoRepository<Reader, String> {
    Reader findByUsername(String username);
}
