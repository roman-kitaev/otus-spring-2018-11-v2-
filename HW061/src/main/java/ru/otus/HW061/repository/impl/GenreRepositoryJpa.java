package ru.otus.HW061.repository.impl;

import org.springframework.stereotype.Repository;
import ru.otus.HW061.domain.Genre;
import ru.otus.HW061.repository.GenreRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class GenreRepositoryJpa implements GenreRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void insert(Genre genre) {
        em.persist(genre);
    }

    @Override
    public Genre getByTitle(String title) {
        return em.find(Genre.class, title);
    }

    @Override
    public List<Genre> getAll() {
        TypedQuery<Genre> query =
                em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery(
                "SELECT count(g) FROM Genre g", Long.class);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void drop() {
        em.createQuery("DELETE FROM Genre g").executeUpdate();
    }
}
