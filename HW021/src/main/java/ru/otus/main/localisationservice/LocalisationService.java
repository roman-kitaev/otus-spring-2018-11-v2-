package ru.otus.main.localisationservice;

import org.springframework.context.MessageSource;

import java.util.Locale;

public interface LocalisationService {
    Locale getLocale();
    MessageSource getMessageSource();
}
