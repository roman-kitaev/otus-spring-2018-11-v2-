package ru.otus.HW121.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "reader")
public class Reader {
    @Id
    private String id;

    @Field(value = "username")
    private String username;

    @Field(value = "password")
    private String password;

    public Reader(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Reader() {
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
