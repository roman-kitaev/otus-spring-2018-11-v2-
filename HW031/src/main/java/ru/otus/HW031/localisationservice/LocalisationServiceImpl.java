package ru.otus.HW031.localisationservice;

import org.springframework.context.MessageSource;

import java.util.Locale;

public class LocalisationServiceImpl implements LocalisationService {
    private MessageSource messageSource;
    private Locale locale;

    public LocalisationServiceImpl(MessageSource messageSource, Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public MessageSource getMessageSource() {
        return messageSource;
    }
}
