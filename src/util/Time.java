package util;

public class Time {

    private long day;
    private int hour;
    private int minute;
    private int seconds;
    private int millis;

    public Time() {
    }

    public Time(long day, int hour, int minute, int seconds, int millis) {
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.seconds = seconds;
        this.millis = millis;
    }

    @Override
    public String toString() {
        return getDay() + " " + addZero(getHour()) + ":" + addZero(getMinute()) + ":" + addZero(getSeconds()) + "." + getMillis();
    }

    private String addZero(long i) {
        return (i >= 0 && i < 10) ? "0" + i : "" + i;
    }

    public long getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMillis() {
        return millis;
    }
}
