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
import ru.otus.HW071.domain.Genre;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class GenreRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GenreRepository genreRepository;

    @Before
    public void deleteAll() {
        genreRepository.deleteAll();
    }

    @Test
    public void contextLoads() {
        genreRepository.save(new Genre("Novel"));
    }

    @Test
    public void insertTest() {
        Genre genre = new Genre("Prose");

        genreRepository.save(genre);

        Genre genreToRetrieve = entityManager.find(Genre.class, genre.getGenre());

        Assert.assertEquals(genre, genreToRetrieve);
    }

    public void retrieveTest() {
        Genre genre = new Genre("Prose");

        entityManager.persist(genre);

        Genre genreToRetrieve = genreRepository.findById(genre.getGenre()).
                orElseThrow(() -> new NoSuchEntityException("Wrong genre ID"));

        Assert.assertEquals(genre, genreToRetrieve);
    }

    @Test
    public void countTest() {
        int n = 100;
        for(int i = 0; i < n; i++) {
            entityManager.persist(new Genre("Genre" + i));
        }
        Assert.assertEquals(n, genreRepository.count());
    }
}
