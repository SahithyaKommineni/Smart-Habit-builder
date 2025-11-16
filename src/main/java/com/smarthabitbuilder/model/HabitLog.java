package com.smarthabitbuilder.model;

import java.time.LocalDate;

public class HabitLog {
    private int id;
    private final int habitId;
    private final LocalDate date;
    private final String status; // COMPLETED or MISSED

    public HabitLog(int habitId, LocalDate date, String status) {
        this.habitId = habitId;
        this.date = date;
        this.status = status;
    }

    public HabitLog(int id, int habitId, LocalDate date, String status) {
        this.id = id;
        this.habitId = habitId;
        this.date = date;
        this.status = status;
    }

    public int getId() { return id; }
    public int getHabitId() { return habitId; }
    public LocalDate getDate() { return date; }
    public String getStatus() { return status; }

    public void setId(int id) { this.id = id; }
}
