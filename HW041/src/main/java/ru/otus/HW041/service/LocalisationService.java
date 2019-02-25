package ru.otus.HW041.service;

public interface LocalisationService {
    void setLocale(String language);
    String getLocalized(String msg, String[] params);
}
