package ru.otus.HW041.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.HW041.service.LocalisationService;
import ru.otus.HW041.properties.QuizerProperties;
import ru.otus.HW041.service.QuestionsLoader;

import java.util.ArrayList;
import java.util.List;

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
        List<String[]> allRows = new ArrayList<>();
        for(int i = 0; i < nQuestions; i++) {
            allRows.add(localisationService.getLocalized(
                    "question." + (i + 1), null).split(","));
        }
        return allRows;
    }
}
