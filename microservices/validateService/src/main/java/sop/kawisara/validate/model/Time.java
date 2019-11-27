package sop.kawisara.validate.model;

public class Time {
        int time;
        int individualTime;

    public Time(int time, int individualTime) {
        this.time = time;
        this.individualTime = individualTime;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getIndividualTime() {
        return individualTime;
    }

    public void setIndividualTime(int individualTime) {
        this.individualTime = individualTime;
    }

    public Time() {}
}
