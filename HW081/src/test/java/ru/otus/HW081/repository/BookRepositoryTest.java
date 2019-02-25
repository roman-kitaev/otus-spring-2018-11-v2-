package ru.otus.HW081.repository;

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
import ru.otus.HW081.domain.Author;
import ru.otus.HW081.domain.Book;
import ru.otus.HW081.domain.Genre;
import ru.otus.HW081.repostory.BookRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@DataMongoTest
public class BookRepositoryTest {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Test
    public void contextLoads() {
        bookRepository.save(new Book("Good Book"));
    }

    @Test
    public void insertTest() {
        String author ="Tolstoy";
        String genre = "Novel";
        String comment1 = "Very very best book";
        String comment2 = "Pretty amazing reading before sleep";

        Book book = new Book("Peace And War");
        book.addGenre(new Genre(genre));
        book.addAuthor(new Author(author));
        book.addComment(comment1);
        book.addComment(comment2);

        bookRepository.save(book);

        Book bookToRetrieve = mongoTemplate.findOne(
                new Query().addCriteria(Criteria.where("booktitle").is(book.getTitle())),
                Book.class
        );

        Assert.assertEquals(book, bookToRetrieve);
    }

    @Test
    public void countTest() {
        int n = 100;
        for(int i = 0; i < n; i++) {
            mongoTemplate.save(new Book("Book" + i));
        }
        Assert.assertEquals(n, bookRepository.count());
    }

    @Test
    public void deleteTest() {
        String author1 ="Pushkin";
        String author2 ="Tolstoy";
        String genre1 = "Novel";
        String genre2 = "Poem";

        Book book1 = new Book("Peace And War");
        book1.addAuthor(new Author(author2));
        book1.addGenre(new Genre(genre1));

        Book book2 = new Book("Ruslan and Ludmila");
        book2.addAuthor(new Author(author1));
        book2.addGenre(new Genre(genre2));

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
        String author ="Tolstoy";
        String genre = "Novel";
        String comment1 = "Very very best book";
        String comment2 = "Pretty amazing reading before sleep";

        Book book = new Book("Peace And War");
        book.addGenre(new Genre(genre));
        book.addAuthor(new Author(author));
        book.addComment(comment1);

        mongoTemplate.save(book);

        bookRepository.addComment(book, comment2);

        Book bookToRetrieve = mongoTemplate.findOne(
                new Query().addCriteria(Criteria.where("booktitle").is(book.getTitle())),
                Book.class
        );

        Assert.assertEquals(Arrays.asList(comment1, comment2),
                bookToRetrieve.getComments());
    }
}
