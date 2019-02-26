package ru.otus.HW091.repostory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.HW091.domain.Genre;

@RunWith(SpringRunner.class)
@DataMongoTest
public class GenreRepositoryTest {

    @Autowired
    MongoTemplate mongoTemplate;

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

        Genre genreToRetrieve = mongoTemplate.findOne(
                new Query().addCriteria(Criteria.where("genre").is(genre.getGenre())),
                Genre.class
        );

        Assert.assertEquals(genre, genreToRetrieve);
    }

    @Test
    public void countTest() {
        int n = 100;
        for(int i = 0; i < n; i++) {
            mongoTemplate.save(new Genre("Prose" + i));
        }
        Assert.assertEquals(n, genreRepository.count());
    }
}
