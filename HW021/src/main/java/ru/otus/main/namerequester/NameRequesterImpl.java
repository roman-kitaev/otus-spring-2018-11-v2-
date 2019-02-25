package ru.otus.main.namerequester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.main.localisationservice.LocalisationService;

import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

@Service
public class NameRequesterImpl implements NameRequester {
    private LocalisationService localisationService;

    @Autowired
    public NameRequesterImpl(LocalisationService localisationService) {
        this.localisationService = localisationService;
    }

    public String askName(InputStream in) {
        MessageSource messageSource = localisationService.getMessageSource();
        Locale locale = localisationService.getLocale();

        System.out.println(messageSource.getMessage("hello.user",
                new String[]{}, locale));

        Scanner scan = new Scanner(in);
        String name = scan.nextLine();

        while(name.length() < 4 || name.split(" ").length < 2) {
            System.out.println(messageSource.getMessage("try.again",
                    new String[]{}, locale));
            name = scan.nextLine();
        }

        System.out.println(messageSource.getMessage("thanks",
                new String[]{name}, locale));
        return name;
    }
}
