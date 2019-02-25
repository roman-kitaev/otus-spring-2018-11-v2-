package ru.otus.HW061.repository;

import ru.otus.HW061.domain.Comment;

public interface CommentRepository {
    void insert(Comment comment);
    Comment getById(int id);
    long count();
    void drop();
}
