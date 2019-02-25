package ru.otus.HW031.display;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.HW031.localisationservice.LocalisationService;

import java.util.List;
import java.util.Locale;

@Service
public class DisplayImpl implements Display{
    private LocalisationService localisationService;

    @Autowired
    public DisplayImpl(LocalisationService localisationService) {
        this.localisationService = localisationService;
    }

    public void displayResults(String userName, List<String[]> quizList,
                               boolean result[]) {
        MessageSource messageSource = localisationService.getMessageSource();
        Locale locale = localisationService.getLocale();

        System.out.println(messageSource.getMessage("results",
                new String[]{userName}, locale));
        
        for(int i = 0; i < quizList.size(); i++) {
            System.out.println(quizList.get(i)[0] + " : " +
                    quizList.get(i)[1] + " " +
                    (result[i] ? "+" : "-"));
        }
    }
}
