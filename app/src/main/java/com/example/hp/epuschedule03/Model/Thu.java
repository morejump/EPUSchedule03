package com.example.hp.epuschedule03.Model;

/**
 * Created by HP on 12/15/2016.
 */

public class Thu {
    private int imagePath;
    private String weekDays;

    public Thu(String weekDays) {
        this.weekDays = weekDays;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

    public String getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(String weekDays) {
        this.weekDays = weekDays;
    }
}
