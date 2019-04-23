package ru.otus.HW151.domain;

import java.util.Random;

public class VeryImportantMessage {
    private static int base = 1;
    private static Random rand = new Random();
    private int id = base++;
    private String cypher;

    public VeryImportantMessage() {
        char[] chars = {(char)rand.nextInt(255), (char)rand.nextInt(255),
                (char)rand.nextInt(255), (char)rand.nextInt(255),
                (char)rand.nextInt(255)};

        cypher = new String(chars);
    }

    @Override
    public String toString() {
        return "VeryImportantMessage{" +
                "id=" + id +
                ", cypher='" + cypher + '\'' +
                '}';
    }
}
