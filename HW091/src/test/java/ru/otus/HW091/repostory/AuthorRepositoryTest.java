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
import ru.otus.HW091.domain.Author;

@RunWith(SpringRunner.class)
@DataMongoTest
public class AuthorRepositoryTest {

    @Autowired
    MongoTemplate mongoTemplate;

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
        Author author = new Author("Marinina");

        authorRepository.save(author);

        Author authorToRetrieve = mongoTemplate.findOne(
                new Query().addCriteria(Criteria.where("name").is(author.getName())),
                Author.class
        );

        Assert.assertEquals(author, authorToRetrieve);
    }

    @Test
    public void countTest() {
        int n = 100;
        for(int i = 0; i < n; i++) {
            mongoTemplate.save(new Author("Author" + i));
        }
        Assert.assertEquals(n, authorRepository.count());
    }
}
