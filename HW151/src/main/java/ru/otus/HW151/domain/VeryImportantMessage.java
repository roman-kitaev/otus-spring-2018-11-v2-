package ru.otus.HW151.domain;

import java.util.Random;

public class VeryImportantMessage {
    private static int base = 1;
    private static Random rand = new Random();
    private final int id = base++;
    private final String msg;

    public VeryImportantMessage() {
        char[] chars = {(char)rand.nextInt(255), (char)rand.nextInt(255),
                (char)rand.nextInt(255), (char)rand.nextInt(255),
                (char)rand.nextInt(255)};

        msg = new String(chars);
    }

    public int getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "VeryImportantMessage{" +
                "id=" + id +
                ", cypher='" + msg + '\'' +
                '}';
    }
}
