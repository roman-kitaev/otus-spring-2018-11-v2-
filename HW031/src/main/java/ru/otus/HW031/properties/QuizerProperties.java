package ru.otus.HW031.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("data")
public class QuizerProperties {
    private int nquestions;
    private String locale;

    public int getNquestions() {
        return nquestions;
    }

    public String getLocale() {
        return locale;
    }

    public void setNquestions(int nquestions) {
        this.nquestions = nquestions;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
