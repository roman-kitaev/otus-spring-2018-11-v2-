package ru.otus.HW031.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.HW031.localisationservice.LocalisationService;
import ru.otus.HW031.properties.QuizerProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class QuestionsLoaderFromCSV implements QuestionsLoader {
    private final int nQuestions;
    private LocalisationService localisationService;

    @Autowired
    public QuestionsLoaderFromCSV(LocalisationService localisationService,
                                  QuizerProperties properties) {
        this.localisationService = localisationService;
        this.nQuestions = properties.getNquestions();
    }

    public List<String[]> makeQuiz() {
        MessageSource messageSource = localisationService.getMessageSource();
        Locale locale = localisationService.getLocale();

        List<String[]> allRows = new ArrayList<>();
        for(int i = 0; i < nQuestions; i++) {
            allRows.add(messageSource.getMessage("question." + (i + 1),
                    new String[]{}, locale).split(","));
        }
        return allRows;
    }
}
