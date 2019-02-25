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

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AuthorRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    @Before
    public void deleteAll() {
        authorRepository.deleteAll();
    }

    @Test
    public void contextLoads() {
        authorRepository.save(new Author("Pushkin"));
    }

    @Test
    public void insertTest() {
        Author author = new Author("Tolstoy");

        authorRepository.save(author);

        int id = (Integer)entityManager.getId(author);
        Author authorToRetrieve = entityManager.find(Author.class, id);

        Assert.assertEquals(author, authorToRetrieve);
    }

    @Test
    public void retrieveByNameTest() {
        Author author = new Author("Tolstoy");

        entityManager.persist(author);

        Author authorToRetrieve = authorRepository.findByName("Tolstoy");

        Assert.assertEquals(author, authorToRetrieve);
    }

    @Test
    public void retrieveByIdTest() {
        Author author = new Author("Tolstoy");

        entityManager.persist(author);
        int id = (Integer)entityManager.getId(author);

        Author authorToRetrieve = authorRepository.findById(id).
                orElseThrow(()->new NoSuchEntityException("Wrong author ID"));

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
