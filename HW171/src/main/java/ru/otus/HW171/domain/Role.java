package ru.otus.HW171.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "role")
public class Role {
    @Id
    private String id;

    @Field(value = "permissions")
    private List<String> permissions;

    public Role() {
    }

    public Role(String id, List<String> permissions) {
        this.id = id;
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}