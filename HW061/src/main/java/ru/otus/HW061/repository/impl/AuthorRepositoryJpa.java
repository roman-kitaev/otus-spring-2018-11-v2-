package ru.otus.HW061.repository.impl;

import org.springframework.stereotype.Repository;
import ru.otus.HW061.domain.Author;
import ru.otus.HW061.repository.AuthorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class AuthorRepositoryJpa implements AuthorRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void insert(Author author) {
        em.persist(author);
    }

    @Override
    public Author getById(int id) {
        return em.find(Author.class, id);
    }

    @Override
    public Author getByName(String name) {
        TypedQuery<Author> query = em.createQuery(
                "select a from Author a where a.name = :name", Author.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query =
                em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery(
                "SELECT count(a) FROM Author a", Long.class);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void drop() {
        em.createQuery("DELETE FROM Author a").executeUpdate();
    }
}
