package com.smarthabitbuilder.dao;


import com.smarthabitbuilder.model.HabitLog;
import java.time.LocalDate;
import java.util.List;


public interface HabitLogDao {
    boolean markCompleted(int habitId, LocalDate date);
    List<HabitLog> getLogsByHabit(int habitId);
    List<HabitLog> getLogsBetween(int habitId, LocalDate start, LocalDate end);
}