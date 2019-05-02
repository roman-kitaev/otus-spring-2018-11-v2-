package ru.otus.HW131.rest;

public class Msg {
    private String msg;

    private Msg(String msg) {this.msg = msg;}

    public static Msg getSuccess() {
        return new Msg("success");
    }

    public static Msg getError() {
        return new Msg("error");
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
