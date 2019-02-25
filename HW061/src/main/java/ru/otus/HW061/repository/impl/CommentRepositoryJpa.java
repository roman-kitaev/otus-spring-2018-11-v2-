package ru.otus.HW061.repository.impl;

import org.springframework.stereotype.Repository;
import ru.otus.HW061.domain.Comment;
import ru.otus.HW061.repository.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@SuppressWarnings("JpaQlInspection")
@Repository
public class CommentRepositoryJpa implements CommentRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void insert(Comment comment) {
        em.persist(comment);
    }

    @Override
    public Comment getById(int id) {
        return em.find(Comment.class, id);
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery(
                "SELECT count(c) FROM Comment c", Long.class);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void drop() {
        em.createQuery("DELETE FROM Comment c").executeUpdate();
    }
}
