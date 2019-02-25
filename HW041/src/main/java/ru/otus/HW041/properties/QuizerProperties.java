package ru.otus.HW041.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("data")
public class QuizerProperties {
    private int nquestions;

    public int getNquestions() {
        return nquestions;
    }

    public void setNquestions(int nquestions) {
        this.nquestions = nquestions;
    }
}
