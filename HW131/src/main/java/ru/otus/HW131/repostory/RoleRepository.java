package ru.otus.HW131.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW131.domain.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
}
