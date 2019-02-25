package ru.otus.HW031.localisationservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.HW031.properties.QuizerProperties;

import java.util.Locale;

@Configuration
public class LocalisationServiceConfig {
    @Bean
    public static LocalisationService getLocalisationService(
            QuizerProperties properties) {
        ReloadableResourceBundleMessageSource ms =
                new ReloadableResourceBundleMessageSource();
        ms.setBasename("bundle");
        ms.setDefaultEncoding("UTF-8");
        return new LocalisationServiceImpl(ms, new Locale(properties.getLocale()));
    }
}
