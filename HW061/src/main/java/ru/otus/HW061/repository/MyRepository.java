package ru.otus.HW061.repository;

import java.util.List;

public interface MyRepository<T> {
    List<T> getAll();
}
