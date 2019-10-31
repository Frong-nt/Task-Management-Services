package com.example.topicservice;

import lombok.Data;

import java.util.*;
@Data
public class Comments {
    private String msg, by, dateTime;

    public Comments(){}
    public Comments(String msg, String by, String dateTime) {
        this.msg = msg;
        this.by = by;
        this.dateTime = dateTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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