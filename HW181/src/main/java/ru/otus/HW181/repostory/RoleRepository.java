package ru.otus.HW181.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW181.domain.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
}
