package com.example.camping_androidv3;

public class CampingActivity {
    private String title;
    private String location;
    private String date;

    public CampingActivity(String title, String location, String date) {
        this.title = title;
        this.location = location;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }
}
