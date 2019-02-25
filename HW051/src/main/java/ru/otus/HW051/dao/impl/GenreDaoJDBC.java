package ru.otus.HW051.dao.impl;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;
import ru.otus.HW051.dao.GenreDao;
import ru.otus.HW051.domain.Genre;

import java.util.HashMap;
import java.util.List;

@Component
public class GenreDaoJDBC implements GenreDao {
    private final NamedParameterJdbcOperations namedJdbc;
    private static RowMapper<Genre> rowMapper = (resultSet, i) -> {
        return new Genre(
                resultSet.getString("genre"));
    };

    public GenreDaoJDBC(NamedParameterJdbcOperations namedJdbc) {
        this.namedJdbc = namedJdbc;
    }

    @Override
    public void initiateTable() {
        namedJdbc.update("DROP TABLE IF EXISTS genres;", new HashMap<>());
        namedJdbc.update("CREATE TABLE genres (\n" +
                "    genre VARCHAR(255) PRIMARY KEY NOT NULL UNIQUE\n" +
                "    );", new HashMap<>());
    }

    @Override
    public void insert(Genre genre) {
        final HashMap<String, Object> params = new HashMap<>(2);
        params.put("genre", genre.getGenre());
        namedJdbc.update("insert into genres (genre) values (:genre)", params);
    }

    @Override
    public void insertGenres(List<Genre> genreList) {
        for(Genre genre : genreList) {
            insert(genre);
        }
    }

    @Override
    public int count() {
        return namedJdbc.queryForObject("select count(*) from genres",
                new HashMap<>(), Integer.class);
    }

    @Override
    public Genre getByGenre(String genre) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("genre", genre);
        return namedJdbc.queryForObject("select * from genres where genre = :genre",
                params, rowMapper);
    }

    @Override
    public void delete(String genre) {
        final HashMap<String, Object> params = new HashMap<>(1);
        params.put("genre", genre);
        namedJdbc.update("delete from genres where genre = :genre",
                params);
    }

    @Override
    public List<Genre> getAll() {
        return namedJdbc.query("select * from genres order by genre asc", rowMapper);
    }
}
