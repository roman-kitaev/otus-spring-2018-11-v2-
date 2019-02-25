package ru.otus.main.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.main.localisationservice.LocalisationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class QuizerCSV implements Quizer {
    private final int nQuestions;
    private LocalisationService localisationService;

    @Autowired
    public QuizerCSV(LocalisationService localisationService,
                     @Value("${n.questions}") int nQuestions) {
        this.localisationService = localisationService;
        this.nQuestions = nQuestions;
    }

    public Quiz makeQuiz() {
        MessageSource messageSource = localisationService.getMessageSource();
        Locale locale = localisationService.getLocale();

        List<String[]> allRows = new ArrayList<>();
        for(int i = 0; i < nQuestions; i++) {
            allRows.add(messageSource.getMessage("question." + (i + 1),
                    new String[]{}, locale).split(","));
        }
        return new Quiz(allRows);
    }
}
