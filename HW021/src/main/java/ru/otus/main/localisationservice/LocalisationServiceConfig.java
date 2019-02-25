package ru.otus.main.localisationservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
@PropertySource("classpath:props.properties")
public class LocalisationServiceConfig {
    @Bean
    public static LocalisationService getLocalisationService(@Value("${data.locale}")String languageTag) {
        ReloadableResourceBundleMessageSource ms =
                new ReloadableResourceBundleMessageSource();
        ms.setBasename("bundle");
        ms.setDefaultEncoding("UTF-8");
        return new LocalisationServiceImpl(ms, new Locale(languageTag));
    }
}
