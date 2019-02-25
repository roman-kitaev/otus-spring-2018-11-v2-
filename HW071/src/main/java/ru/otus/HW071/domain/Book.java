package ru.otus.HW071.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "book")
public class Book implements HasDescription{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "booktitle")
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "BOOK_AUTHOR",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID")
    )
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "BOOK_GENRE",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "GENRE_ID")
    )
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public void addGenre(Genre genre) {
        this.genres.add(genre);
    }

    public void addComment(Comment comment) {
        if(!comments.contains(comment)) {
            comments.add(comment);
        }
        if(comment.getBook() != this) {
            comment.setBook(this);
        }
    }

    @Override
    public String getEntityDescription() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("BOOK  " + getTitle() + " by ");
        if(!authors.isEmpty()) {
            for(Author author : authors) {
                stringBuilder.append(author.getName() + " ");
            }
        } else {
            stringBuilder.append("[author is not defined or unknown]");
        }
        stringBuilder.append(", it is a ");
        if(!genres.isEmpty()) {
            for(Genre genre : genres) {
                stringBuilder.append(genre.getGenre() + " ");
            }
        } else {
            stringBuilder.append("[genre is not specified or unknown]");
        }
        return stringBuilder.toString();
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

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
