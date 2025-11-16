package com.smarthabitbuilder.service;

import com.smarthabitbuilder.dao.HabitLogDao;
import com.smarthabitbuilder.model.HabitLog;

import java.time.LocalDate;
import java.util.List;

public class HabitLogServiceImpl extends HabitLogService {

    private final HabitLogDao habitLogDao;

    public HabitLogServiceImpl(HabitLogDao habitLogDao) {
        super();
        this.habitLogDao = habitLogDao;
    }

    public boolean markCompleted(int habitId, LocalDate date) {
        return habitLogDao.markCompleted(habitId, date);
    }

    @Override
    public List<HabitLog> getLogsByHabit(int habitId) {
        return habitLogDao.getLogsByHabit(habitId);
    }

    @Override
    public List<HabitLog> getLogsBetween(int habitId, LocalDate start, LocalDate end) {
        return habitLogDao.getLogsBetween(habitId, start, end);
    }
}
