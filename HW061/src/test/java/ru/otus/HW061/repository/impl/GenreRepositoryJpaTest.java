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
import ru.otus.HW061.domain.Genre;
import ru.otus.HW061.repository.GenreRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableAutoConfiguration
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@EntityScan(basePackageClasses = Genre.class)
@ContextConfiguration(classes = GenreRepositoryJpa.class)

public class GenreRepositoryJpaTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GenreRepository genreRepository;

    @Before
    public void dropTable() {
        genreRepository.drop();
    }

    @Test
    public void contextLoads() {
        genreRepository.insert(new Genre("Novel"));
    }

    @Test
    public void insertTest() {
        Genre genre = new Genre("Prose");

        genreRepository.insert(genre);

        Genre genreToRetrieve = entityManager.find(Genre.class, genre.getGenre());

        Assert.assertEquals(genre, genreToRetrieve);
    }

    public void retrieveTest() {
        Genre genre = new Genre("Prose");

        entityManager.persist(genre);

        Genre genreToRetrieve = genreRepository.getByTitle(genre.getGenre());

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
