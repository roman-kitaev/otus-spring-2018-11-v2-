package ru.otus.HW041.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.HW041.service.LocalisationService;
import ru.otus.HW041.service.NameRequester;

import java.io.InputStream;
import java.util.Scanner;

@Service
public class NameRequesterImpl implements NameRequester {
    private LocalisationService localisationService;

    @Autowired
    public NameRequesterImpl(LocalisationService localisationService) {
        this.localisationService = localisationService;
    }

    public String askName(InputStream in) {
        System.out.println(
                localisationService.getLocalized("hello.user", null));

        Scanner scan = new Scanner(in);
        String name = scan.nextLine();

        while(name.length() < 4 || name.split(" ").length < 2) {
            System.out.println(
                localisationService.getLocalized("try.again", null));
            name = scan.nextLine();
        }
        System.out.println(
            localisationService.getLocalized("thanks", new String[]{name}));
        return name;
    }
}
