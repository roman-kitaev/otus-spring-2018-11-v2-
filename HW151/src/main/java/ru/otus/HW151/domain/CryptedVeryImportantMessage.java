package ru.otus.HW151.domain;

public class CryptedVeryImportantMessage {
    private final int id;
    private final String cryptedMsg;

    public CryptedVeryImportantMessage(int id, String cryptedMsg) {
        this.id = id;
        this.cryptedMsg = cryptedMsg;
    }

    public String getCryptedMsg() {
        return cryptedMsg;
    }

    public int getId() {
        return id;
    }

    public Boolean isIdEven() {
        return id % 2 == 0;
    }

    public Boolean isNotIdDevidedByFour() {
        return id % 4 != 0;
    }

    @Override
    public String toString() {
        return "CryptedVeryImportantMessage{" +
                "id=" + id +
                ", cryptedMsg='" + cryptedMsg + '\'' +
                '}';
    }
}
