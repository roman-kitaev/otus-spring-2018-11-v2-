package ru.otus.HW171.rest.dto;

import ru.otus.HW171.domain.Book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookDto {
    private String msg;

    private String id;

    private String title;

    private Set<String> authors = new HashSet<>();

    private Set<String> genres = new HashSet<>();

    private List<String> comments = new ArrayList<>();

    public BookDto() {
    }

    public BookDto(String id, String title, Set<String> authors, Set<String> genres, List<String> comments) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.genres = genres;
        this.comments = comments;
    }

    public void setMsgSuccess() {
        msg = "success";
    }

    public String getMsg() {
        return msg;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(Set<String> authors) {
        this.authors = authors;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public static BookDto toDto(Book book) {
        return new BookDto(book.getId(),
                book.getTitle(),
                book.getAuthors(),
                book.getGenres(),
                book.getComments());
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "msg='" + msg + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                ", comments=" + comments +
                '}';
    }
}
