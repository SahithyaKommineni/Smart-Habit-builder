package com.smarthabitbuilder.service;

import com.smarthabitbuilder.dao.HabitLogDao;
import com.smarthabitbuilder.model.HabitLog;

import java.time.LocalDate;
import java.util.List;

public class HabitLogService {

    private HabitLogDao habitLogDao;

    public HabitLogService() {
        this.habitLogDao = habitLogDao;
    }

    // Mark a habit as completed for today's date
    public boolean markCompleted(int habitId) {
        return habitLogDao.markCompleted(habitId, LocalDate.now());
    }

    // Mark a habit for a specific date
    public boolean markCompletedOnDate(int habitId, LocalDate date) {
        return habitLogDao.markCompleted(habitId, date);
    }

    // Get all logs for a habit
    public List<HabitLog> getLogsByHabit(int habitId) {
        return habitLogDao.getLogsByHabit(habitId);
    }

    // Get logs of one habit between date range
    public List<HabitLog> getLogsBetween(int habitId, LocalDate startDate, LocalDate endDate) {
        return habitLogDao.getLogsBetween(habitId, startDate, endDate);
    }
}
