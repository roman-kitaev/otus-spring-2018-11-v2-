package ru.otus.HW061.repository.impl;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.HW061.domain.Author;
import ru.otus.HW061.repository.AuthorRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableAutoConfiguration
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@EntityScan(basePackageClasses = Author.class)
@ContextConfiguration(classes = AuthorRepositoryJpa.class)

public class AuthorRepositoryJpaTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    @Before
    public void dropTable() {
        authorRepository.drop();
    }

    @Test
    public void contextLoads() {
        authorRepository.insert(new Author("Pushkin"));
    }

    @Test
    public void insertTest() {
        Author author = new Author("Tolstoy");

        authorRepository.insert(author);

        int id = (Integer)entityManager.getId(author);
        Author authorToRetrieve = entityManager.find(Author.class, id);

        Assert.assertEquals(author, authorToRetrieve);
    }

    @Test
    public void retrieveByNameTest() {
        Author author = new Author("Tolstoy");

        entityManager.persist(author);

        Author authorToRetrieve = authorRepository.getByName("Tolstoy");

        Assert.assertEquals(author, authorToRetrieve);
    }

    @Test
    public void retrieveByIdTest() {
        Author author = new Author("Tolstoy");

        entityManager.persist(author);
        int id = (Integer)entityManager.getId(author);

        Author authorToRetrieve = authorRepository.getById(id);

        Assert.assertEquals(author, authorToRetrieve);
    }

    @Test
    public void countTest() {
        int n = 100;
        for(int i = 0; i < n; i++) {
            entityManager.persist(new Author("Author" + i));
        }
        Assert.assertEquals(n, authorRepository.count());
    }
}
