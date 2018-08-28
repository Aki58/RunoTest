package com.example.aki.runotest;

public class user {
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String number;
    private String time;
    private String date;
    private String type;

    public user(String number, String time, String date, String type) {
        this.number = number;
        this.time = time;
        this.date = date;
        this.type = type;
    }
}
