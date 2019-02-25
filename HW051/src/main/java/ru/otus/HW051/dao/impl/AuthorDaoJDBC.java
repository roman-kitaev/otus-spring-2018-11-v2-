package ru.otus.HW051.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.HW051.dao.AuthorDao;
import ru.otus.HW051.domain.Author;

import java.util.HashMap;
import java.util.List;

@Repository
public class AuthorDaoJDBC implements AuthorDao {
    private final NamedParameterJdbcOperations namedJdbc;
    private static RowMapper<Author> rowMapper = (resultSet, i) -> {
        return new Author(
                resultSet.getInt("id"),
                resultSet.getString("name"));
    };

    public AuthorDaoJDBC(NamedParameterJdbcOperations namedJdbc) {
        this.namedJdbc = namedJdbc;
    }

    @Override
    public void initiateTable() {
        namedJdbc.update("DROP TABLE IF EXISTS authors;", new HashMap<>());
        namedJdbc.update("CREATE TABLE authors (\n" +
                        "    id INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                        "    name VARCHAR(255) NOT NULL\n" +
                        "    );", new HashMap<>());
    }

    @Override
    public void insert(Author author) {
        final HashMap<String, Object> params = new HashMap<>(2);
        params.put("name", author.getName());
        namedJdbc.update("insert into authors (name) values (:name)", params);
    }

    @Override
    public void insertAuthors(List<Author> authorList) {
        for(Author author : authorList) {
            insert(author);
        }
    }

    @Override
    public int count() {
        return namedJdbc.queryForObject("select count(*) from authors",
                new HashMap<>(), Integer.class);
    }

    @Override
    public Author getById(int id) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return namedJdbc.queryForObject("select * from authors where id = :id",
                params, rowMapper);
    }

    @Override
    public void delete(int id) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        namedJdbc.update("delete from authors where id = :id",
                params);
    }

    @Override
    public List<Author> getAll() {
        return namedJdbc.query("select * from authors order by id asc", rowMapper);
    }
}
