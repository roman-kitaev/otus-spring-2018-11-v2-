package ru.otus.HW061.repository.impl;

import org.springframework.stereotype.Repository;
import ru.otus.HW061.domain.Book;
import ru.otus.HW061.repository.BookRepository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
public class BookRepositoryJpa implements BookRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void insert(Book book) {
        em.persist(book);
    }

    @Override
    public void insertBooks(List<Book> bookList) {
        for(Book book : bookList) {
            insert(book);
        }
    }

    @Override
    public Book getById(int id) {
        return em.find(Book.class, id);
    }

    @Override
    public Book getByTitle(String title) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where b.title = :title", Book.class);
        query.setParameter("title", title);
        return query.getSingleResult();
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> query =
                em.createQuery("select b from Book b", Book.class);
        return query.getResultList();
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery(
                "SELECT count(b) FROM Book b", Long.class);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void delete(int id) {
        em.remove(em.find(Book.class, id));
    }

    @Override
    public void drop() {
        em.createQuery("DELETE FROM Book b").executeUpdate();
    }
}
