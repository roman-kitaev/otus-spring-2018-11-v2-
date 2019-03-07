package ru.otus.HW101.rest;

public class Msg {
    private String msg;

    private Msg(String msg) {this.msg = msg;}

    public static Msg getSuccess() {
        return new Msg("success");
    }

    public static Msg getError() {
        return new Msg("error");
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
