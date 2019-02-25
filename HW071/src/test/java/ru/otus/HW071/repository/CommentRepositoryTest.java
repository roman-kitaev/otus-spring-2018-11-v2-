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
import ru.otus.HW071.domain.Comment;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CommentRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentRepository commentRepository;

    @Before
    public void deleteAll() {
        commentRepository.deleteAll();
    }

    @Test
    public void contextLoads() {
        commentRepository.save(new Comment("Bla bla bla"));
    }

    @Test
    public void insertTest() {
        Comment comment = new Comment("Bla bla bla");

        commentRepository.save(comment);

        int id = (Integer)entityManager.getId(comment);
        Comment commentToRetrieve = entityManager.find(Comment.class, id);

        Assert.assertEquals(comment, commentToRetrieve);
    }

    @Test
    public void retrieveByIdTest() {
        Comment comment = new Comment("Bla bla bla");

        entityManager.persist(comment);
        int id = (Integer)entityManager.getId(comment);

        Comment commentToRetrieve = commentRepository.findById(id).
                orElseThrow(() -> new NoSuchEntityException("Wrong comment ID"));

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
