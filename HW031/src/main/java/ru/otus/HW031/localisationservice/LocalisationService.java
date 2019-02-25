package ru.otus.HW031.localisationservice;

import org.springframework.context.MessageSource;

import java.util.Locale;

public interface LocalisationService {
    Locale getLocale();
    MessageSource getMessageSource();
}
