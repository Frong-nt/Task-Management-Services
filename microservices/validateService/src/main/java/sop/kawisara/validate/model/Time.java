package sop.kawisara.validate.model;

public class Time {
        int time;
        int bossTime;

    public Time(int time, int bossTime) {
        this.time = time;
        this.bossTime = bossTime;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getBossTime() {
        return bossTime;
    }

    public void setBossTime(int bossTime) {
        this.bossTime = bossTime;
    }

    public Time() {}
}
