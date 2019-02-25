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
import ru.otus.HW061.domain.Comment;
import ru.otus.HW061.repository.CommentRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@EnableAutoConfiguration
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@EntityScan(basePackageClasses = Comment.class)
@ContextConfiguration(classes = CommentRepositoryJpa.class)

public class CommentRepositoryJpaTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentRepository commentRepository;

    @Before
    public void dropTable() {
        commentRepository.drop();
    }

    @Test
    public void contextLoads() {
        commentRepository.insert(new Comment("Bla bla bla"));
    }

    @Test
    public void insertTest() {
        Comment comment = new Comment("Bla bla bla");

        commentRepository.insert(comment);

        int id = (Integer)entityManager.getId(comment);
        Comment commentToRetrieve = entityManager.find(Comment.class, id);

        Assert.assertEquals(comment, commentToRetrieve);
    }

    @Test
    public void retrieveByIdTest() {
        Comment comment = new Comment("Bla bla bla");

        entityManager.persist(comment);
        int id = (Integer)entityManager.getId(comment);

        Comment commentToRetrieve = commentRepository.getById(id);

        Assert.assertEquals(comment, commentToRetrieve);
    }

    @Test
    public void countTest() {
        int n = 100;
        for(int i = 0; i < n; i++) {
            entityManager.persist(new Comment("bla bla"));
        }
        Assert.assertEquals(n, commentRepository.count());
    }
}