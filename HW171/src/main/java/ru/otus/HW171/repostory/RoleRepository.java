package ru.otus.HW171.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW171.domain.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
}
