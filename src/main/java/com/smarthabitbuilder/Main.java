package com.smarthabitbuilder;

import com.smarthabitbuilder.dao.impl.*;
import com.smarthabitbuilder.model.Habit;
import com.smarthabitbuilder.service.*;
import com.smarthabitbuilder.ui.CLI;

public class Main {
    public static void main(String[] args) {
        HabitDaoImpl habitDao;
        habitDao = new HabitDaoImpl() {
            @Override
            public boolean updateHabit(Habit habit) {
                return false;
            }

            @Override
            public boolean deleteHabit(int id) {
                return false;
            }

            @Override
            public Habit getHabitById(int id) {
                return null;
            }

            @Override
            public boolean updateHabit(int id, String newName, String newFrequency, String newCategory) {
                return false;
            }
        };
        HabitLogDaoImpl habitLogDao = new HabitLogDaoImpl();

        HabitService habitService = new HabitServiceImpl(habitDao);
        HabitLogService habitLogService = new HabitLogServiceImpl(habitLogDao);
        AnalyticsService analyticsService = new AnalyticsServiceImpl();

        CLI cli = new CLI(habitService, habitLogService, analyticsService);
        cli.start();
    }
}
