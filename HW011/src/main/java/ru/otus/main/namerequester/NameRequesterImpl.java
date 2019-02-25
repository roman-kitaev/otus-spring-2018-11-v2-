package ru.otus.main.namerequester;

import java.io.InputStream;
import java.util.Scanner;

public class NameRequesterImpl implements NameRequester{
    public String askName(InputStream in) {
        System.out.println("Hello!\nPlease enter your name and surname!");

        Scanner scan = new Scanner(in);
        String name = scan.nextLine();

        while(name.length() < 4 || name.split(" ").length < 2) {
            System.out.println("Please try again!!");
            name = scan.nextLine();
        }
        return name;
    }
}
