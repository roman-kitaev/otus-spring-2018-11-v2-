package ru.otus.HW091.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;
import java.util.stream.Collectors;

@Document(collection = "book")
public class Book implements HasDescription{
    @Id
    private String id;

    @Field(value = "title")
    private String title;

    @Field(value = "authors")
    private Set<String> authors = new HashSet<>();

    @Field(value = "genres")
    private Set<String> genres = new HashSet<>();

    @Field(value = "comments")
    private List<String> comments = new ArrayList<>();

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public void addAuthor(Author author) {
        this.authors.add(author.getName());
    }

    public void addGenre(Genre genre) {
        this.genres.add(genre.getGenre());
    }

    public void addComment(String comment) {
        this.comments.add(comment);
    }

    @Override
    public String getEntityDescription() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Book '" + getTitle() + "' by ");
        if(!authors.isEmpty()) {
            for(String author : authors) {
                stringBuilder.append(author + ", ");
            }
        } else {
            stringBuilder.append("[author is not defined or unknown]");
        }
        stringBuilder.append("it is a ");
        if(!genres.isEmpty()) {
            for(String genre : genres) {
                stringBuilder.append(genre + ", ");
            }
        } else {
            stringBuilder.append("[genre is not specified or unknown]  ");
        }
        return stringBuilder.toString().substring(0, stringBuilder.length() - 2);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(genres, book.genres) &&
                Objects.equals(comments, book.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authors, genres, comments);
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

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(Set<String> authors) {
        this.authors = authors;
    }

    public void setAuthorsFromWebInput(String newAuthors, Collection<Author> availableAuthors) {
        Set<Author> currentAuthors = prepareNewPropertiesList(newAuthors).stream().
                map(str -> new Author(str)).collect(Collectors.toSet());
        currentAuthors.retainAll(availableAuthors);
        authors.clear();
        currentAuthors.forEach(author -> addAuthor(author));
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public void setGenresFromWebInput(String newGenres, Collection<Genre> availableGenres) {
        Set<Genre> currentGenres = prepareNewPropertiesList(newGenres).stream().
                map(str -> new Genre(str)).collect(Collectors.toSet());
        currentGenres.retainAll(availableGenres);
        genres.clear();
        currentGenres.forEach(genre -> addGenre(genre));
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public void setCommentsFromWebInput(String newComments) {
        List<String> currentComments = prepareNewPropertiesList(newComments);
        comments = currentComments;
    }

    private List<String> prepareNewPropertiesList(String newPropertiesString) {
        newPropertiesString = newPropertiesString.replace("[", "").
                replace("]", "").
                replace(", ", ",");
        String[] newPropertiesStrArray = newPropertiesString.split(",");
        return Arrays.asList(newPropertiesStrArray);
    }
}
