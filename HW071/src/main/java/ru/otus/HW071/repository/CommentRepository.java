package ru.otus.HW071.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.HW071.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
