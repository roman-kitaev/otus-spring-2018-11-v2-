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
import ru.otus.HW091.domain.Book;
import ru.otus.HW091.domain.Genre;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@DataMongoTest
public class BookRepositoryTest {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private BookRepository bookRepository;

    private Book book1, book2, book3;

    @Before
    public void deleteAndCreate() {
        bookRepository.deleteAll();

        String author1 ="Pushkin";
        String author2 ="Tolstoy";
        String genre1 = "Novel";
        String genre2 = "Poem";

        String comment1 = "Very very best book";
        String comment2 = "Pretty amazing reading before sleep";

        book1 = new Book("Peace And War");
        book1.addAuthor(new Author(author2));
        book1.addGenre(new Genre(genre1));
        book1.addComment(comment1);
        book1.addComment(comment2);

        book2 = new Book("Ruslan and Ludmila");
        book2.addAuthor(new Author(author1));
        book2.addGenre(new Genre(genre2));
    }

    @Test
    public void contextLoads() {
        bookRepository.save(book1);
    }

    @Test
    public void insertTest() {
        bookRepository.save(book1);

        Book bookToRetrieve = mongoTemplate.findOne(
                new Query().addCriteria(Criteria.where("title").is(book1.getTitle())),
                Book.class
        );

        Assert.assertEquals(book1, bookToRetrieve);
    }

    @Test
    public void countTest() {
        int n = 100;

        Stream.iterate(1, k -> k + 1).limit(100).
                forEach(k -> mongoTemplate.save(new Book("Book" + k)));

        Assert.assertEquals(n, bookRepository.count());
    }

    @Test
    public void deleteTest() {
        mongoTemplate.save(book1);
        mongoTemplate.save(book2);

        long countBefore = mongoTemplate.count(new Query(), Book.class);

        bookRepository.delete(book1);

        long countAfter = mongoTemplate.count(new Query(), Book.class);

        Assert.assertEquals(countAfter, countBefore - 1);
    }

    @Test
    public void findBooksByAuthorsAndGenresTest_1() {
        String author1 ="Pushkin";
        String author2 ="Tolstoy";
        String genre1 = "Novel";
        String genre2 = "Poem";

        Book book1 = new Book("Peace And War");
        book1.addAuthor(new Author(author1));

        Book book2 = new Book("Ruslan and Ludmila");
        book2.addGenre(new Genre(genre1));

        Book book3 = new Book("TestBook");
        book3.addAuthor(new Author(author1));
        book3.addAuthor(new Author(author2));
        book3.addGenre(new Genre(genre1));
        book3.addGenre(new Genre(genre2));

        mongoTemplate.save(book1);
        mongoTemplate.save(book2);
        mongoTemplate.save(book3);

        List<Book> booksToRetrieve = bookRepository.findBooksByAuthorsAndGenres(
          Arrays.asList(author1),
          Arrays.asList()
        );
        List<Book> booksToMatch = new ArrayList<>();
        booksToMatch.add(book1);
        booksToMatch.add(book3);

        Assert.assertEquals(booksToMatch, booksToRetrieve);
    }

    @Test
    public void findBooksByAuthorsAndGenresTest_2() {
        String author1 ="Pushkin";
        String author2 ="Tolstoy";
        String genre1 = "Novel";
        String genre2 = "Poem";

        Book book1 = new Book("Peace And War");
        book1.addAuthor(new Author(author1));

        Book book2 = new Book("Ruslan and Ludmila");
        book2.addGenre(new Genre(genre1));

        Book book3 = new Book("TestBook");
        book3.addAuthor(new Author(author1));
        book3.addAuthor(new Author(author2));
        book3.addGenre(new Genre(genre1));
        book3.addGenre(new Genre(genre2));

        mongoTemplate.save(book1);
        mongoTemplate.save(book2);
        mongoTemplate.save(book3);

        List<Book> booksToRetrieve = bookRepository.findBooksByAuthorsAndGenres(
                Arrays.asList(author1, author2),
                Arrays.asList(genre1)
        );
        List<Book> booksToMatch = new ArrayList<>();
        booksToMatch.add(book3);

        Assert.assertEquals(booksToMatch, booksToRetrieve);
    }

    @Test
    public void addCommentTest() {
        String comment1 = "Very very best book";
        String comment2 = "Pretty amazing reading before sleep";
        String comment3 = "Will buy two more!";

        book2.addComment(comment1);
        book2.addComment(comment2);

        mongoTemplate.save(book2);

        bookRepository.addComment(book2, comment3);

        Book bookToRetrieve = mongoTemplate.findOne(
                new Query().addCriteria(Criteria.where("title").is(book2.getTitle())),
                Book.class
        );

        Assert.assertEquals(Arrays.asList(comment1, comment2, comment3),
                bookToRetrieve.getComments());
    }
}
