package ru.otus.HW111.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.HW111.domain.Author;
import ru.otus.HW111.domain.Book;
import ru.otus.HW111.domain.Genre;

import java.util.List;

public class BookRepositoryCustomImpl implements BookRepositoryCustom {
    @Autowired
    ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Flux<Book> findBooksByAuthorsAndGenres(List<String> authors, List<String> genres) {
        Criteria stubCriteria = Criteria.where("title").exists(true);
        Query query = new Query();

        query.addCriteria(stubCriteria.
                andOperator(
                        (genres.isEmpty() ?
                                stubCriteria : Criteria.where("genres").all(genres)),
                        (authors.isEmpty() ?
                                stubCriteria : Criteria.where("authors").all(authors))
                ));

        return reactiveMongoTemplate.find(query, Book.class);
    }

    @Override
    public Mono<Void> addComment(Book book, String comment) {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(book.getTitle()));
        Update update = new Update();
        update.push("comments", comment);
        return reactiveMongoTemplate.updateFirst(query, update, Book.class).then();
    }

    @Override
    public Flux<Book> findBooksByGenre(Genre genre) {
        Query query = new Query();
        query.addCriteria(Criteria.where("genres").in(genre.getGenre()));
        return reactiveMongoTemplate.find(query, Book.class);
    }

    @Override
    public Flux<Book> findBooksByAuthor(Author author) {
        Query query = new Query();
        query.addCriteria(Criteria.where("authors").in(author.getName()));
        return reactiveMongoTemplate.find(query, Book.class);
    }
}
