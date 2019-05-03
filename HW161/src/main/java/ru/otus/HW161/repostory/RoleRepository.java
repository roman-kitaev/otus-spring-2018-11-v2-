package ru.otus.HW161.repostory;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.HW161.domain.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
}
