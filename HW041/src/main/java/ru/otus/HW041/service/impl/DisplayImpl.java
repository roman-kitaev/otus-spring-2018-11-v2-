package ru.otus.HW041.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.HW041.service.LocalisationService;
import ru.otus.HW041.service.Display;

import java.util.List;

@Service
public class DisplayImpl implements Display {
    private LocalisationService localisationService;

    @Autowired
    public DisplayImpl(LocalisationService localisationService) {
        this.localisationService = localisationService;
    }

    public void displayResults(String userName, List<String[]> quizList,
                               boolean result[]) {
        System.out.println(
            localisationService.getLocalized("results", new String[]{userName}));
        
        for(int i = 0; i < quizList.size(); i++) {
            System.out.println(quizList.get(i)[0] + " : " +
                    quizList.get(i)[1] + " " +
                    (result[i] ? "+" : "-"));
        }
    }
}
