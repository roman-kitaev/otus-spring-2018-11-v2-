package ru.otus.HW061.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private int id;
    private String comment;

    @ManyToOne
    @JoinColumn(name="BOOK_ID")
    private Book book;

    public Comment() {
    }

    public Comment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public Book getBook() {
        return book;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setBook(Book book) {
        this.book = book;
        book.addComment(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return Objects.equals(comment, comment1.comment) &&
                Objects.equals(book, comment1.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, book);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment='" + comment + '\'' +
                '}';
    }
}
