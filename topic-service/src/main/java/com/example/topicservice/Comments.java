package com.example.topicservice;

import lombok.Data;

import java.util.*;
@Data
public class Comments {
    private String message, by, dateTime;

    public Comments(){}
    public Comments(String message, String by, String dateTime) {
        this.message = message;
        this.by = by;
        this.dateTime = dateTime;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String message) {
        this.message = message;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}