package ru.otus.HW051.dao.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.HW051.dao.AuthorDao;
import ru.otus.HW051.dao.BookDao;
import ru.otus.HW051.dao.GenreDao;
import ru.otus.HW051.domain.Author;
import ru.otus.HW051.domain.Book;
import ru.otus.HW051.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class BookDaoJDBC implements BookDao {
    private final NamedParameterJdbcOperations namedJdbc;

    private static RowMapper<Book> rowMapper = (resultSet, i) -> {
        Author author = new Author(
          resultSet.getInt(3),
          resultSet.getString(4));

        Genre genre = new Genre(
          resultSet.getString(5));

        return new Book(
          resultSet.getInt(1),
          resultSet.getString(2), author, genre);
    };

    public BookDaoJDBC(NamedParameterJdbcOperations namedJdbc) {
        this.namedJdbc = namedJdbc;
    }

    @Override
    public void dropTable() {
        namedJdbc.update("DROP TABLE IF EXISTS books;", new HashMap<>());
    }

    @Override
    public void initiateTable() {
        dropTable();
        namedJdbc.update("CREATE TABLE books\n" +
                "(id INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                "title VARCHAR(255) NOT NULL UNIQUE,\n" +
                "authors_id INTEGER NOT NULL,\n" +
                "genres_genre VARCHAR(255) NOT NULL,\n" +
                "FOREIGN KEY (authors_id) REFERENCES authors(id),\n" +
                "FOREIGN KEY (genres_genre) REFERENCES genres(genre));", new HashMap<>());
    }

    @Override
    public void insert(Book book) {
        final HashMap<String, Object> params = new HashMap<>(4);
        params.put("title", book.getTitle());
        params.put("authors_id", book.getAuthor().getId());
        params.put("genres_genre", book.getGenre().getGenre());
        namedJdbc.update("insert into books (title, authors_id, genres_genre)" +
                " values (:title, :authors_id, :genres_genre)", params);
    }

    @Override
    public Book getById(int id) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        Book book = null;
        try {
            book = namedJdbc.queryForObject("select books.id, books.title, authors.id, authors.name, \n" +
                            "genres.genre from books\n" +
                            "left join authors on (authors.id = books.authors_id)\n" +
                            "left join genres on (genres.genre = books.genres_genre) where books.id = :id;",
                    params, rowMapper);
        } catch (EmptyResultDataAccessException ex) {
            System.out.println("Wrong id!");
        }
        return book;
    }

    @Override
    public Book getByTitle(String title) {
        final HashMap<String, Object> params = new HashMap<>(1);
        Integer id = null;
        Book book = null;
        params.put("title", title);
        try {
             id = namedJdbc.queryForObject("select id from books where title = :title;",
                    params, (resultSet, i) -> resultSet.getInt("id"));
        } catch (EmptyResultDataAccessException ex) {
            System.out.println("Wrong title!");
        }
        return getById(id);
    }

    @Override
    public List<Book> getAll() {
        List<Integer> idList = namedJdbc.query("select id from books;",
                (resultSet, i) -> resultSet.getInt("id"));
        List<Book> bookList = new ArrayList<>();

        for(int i : idList) {
            bookList.add(getById(i));
        }
        return bookList;
    }

    @Override
    public void delete(int id) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        namedJdbc.update("delete from books where id = :id",
                params);
    }

    @Override
    public int count() {
        return namedJdbc.queryForObject("select count(*) from books",
                new HashMap<>(), Integer.class);
    }

    @Override
    public void insertBooks(List<Book> bookList) {
        for(Book book : bookList) {
            insert(book);
        }
    }
}
