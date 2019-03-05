package ru.otus.HW101.repostory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.HW101.domain.Author;
import ru.otus.HW101.domain.Book;
import ru.otus.HW101.domain.Genre;

import java.util.List;

public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void addComment(Book book, String comment) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(book.getTitle()));
        Update update = new Update();
        update.push("comments", comment);
        mongoTemplate.updateFirst(query, update, Book.class);
    }

    @Override
    public List<Book> findBooksByAuthorsAndGenres(List<String> authors,
                                                  List<String> genres) {
        Criteria stubCriteria = Criteria.where("title").exists(true);
        Query query = new Query();

        query.addCriteria(stubCriteria.
                andOperator(
                        (genres.isEmpty() ?
                                stubCriteria : Criteria.where("genres").all(genres)),
                        (authors.isEmpty() ?
                                stubCriteria : Criteria.where("authors").all(authors))
                ));

        return mongoTemplate.find(query, Book.class);
    }

    @Override
    public List<Book> findBooksByGenre(Genre genre) {
        Query query = new Query();
        query.addCriteria(Criteria.where("genres").in(genre.getGenre()));
        return mongoTemplate.find(query, Book.class);
    }

    @Override
    public List<Book> findBooksByAuthor(Author author) {
        Query query = new Query();
        query.addCriteria(Criteria.where("authors").in(author.getName()));
        return mongoTemplate.find(query, Book.class);
    }
}
