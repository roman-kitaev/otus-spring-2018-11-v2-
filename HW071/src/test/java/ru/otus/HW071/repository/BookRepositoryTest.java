package ru.otus.HW071.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.HW071.domain.Author;
import ru.otus.HW071.domain.Book;
import ru.otus.HW071.domain.Comment;
import ru.otus.HW071.domain.Genre;

import java.util.Random;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    Random rand = new Random(47);

    @Before
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Test
    public void contextLoads() {
        Book book = new Book("Peace And War");
        bookRepository.save(book);
    }

    @Test
    public void insertTest() {
        Author author = new Author("Tolstoy");
        Genre genre = new Genre("Novel");
        Comment comment1 = new Comment("Very best book");
        Comment comment2 = new Comment("Pretty amazingly good reading");

        entityManager.persist(author);
        entityManager.persist(genre);

        Book book = new Book("Peace And War");
        book.addGenre(genre);
        book.addAuthor(author);

        //***********************************//
        bookRepository.save(book);
        //***********************************//

        book.addComment(comment1);
        book.addComment(comment2);

        entityManager.persist(comment1);
        entityManager.persist(comment2);

        int id = (Integer)entityManager.getId(book);
        Book bookToRetrieve = entityManager.find(Book.class, id);

        Assert.assertEquals(book, bookToRetrieve);
    }

    @Test
    public void countTest() {
        int n = 100;
        for(int i = 0; i < n; i++) {
            entityManager.persist(new Book("Book" + i));
        }
        Assert.assertEquals(n, bookRepository.count());
    }

    @Test
    public void deleteTest() {
        Author author1 = new Author("Pushkin");
        Author author2 = new Author("Tolstoy");
        Genre genre1 = new Genre("novel");
        Genre genre2 = new Genre("poem");
        entityManager.persist(author1);
        entityManager.persist(author2);
        entityManager.persist(genre1);
        entityManager.persist(genre2);


        Book book1 = new Book("Peace And War");
        book1.addAuthor(author2);
        book1.addGenre(genre1);

        Book book2 = new Book("Ruslan and Ludmila");
        book2.addAuthor(author1);
        book2.addGenre(genre2);

        int idToDelete = (Integer)entityManager.persistAndGetId(book1);
        entityManager.persist(book2);

        long countBefore = bookRepository.count();

        bookRepository.deleteById(idToDelete);

        long countAfter = bookRepository.count();

        Assert.assertEquals(countAfter, countBefore - 1);
    }
}
