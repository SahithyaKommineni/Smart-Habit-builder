package com.smarthabitbuilder.service;

import com.smarthabitbuilder.dao.HabitDao;
import com.smarthabitbuilder.model.Habit;

import java.util.List;

public class HabitServiceImpl implements HabitService {

    private final HabitDao habitDao;

    public HabitServiceImpl(HabitDao habitDao) {
        this.habitDao = habitDao;
    }

    @Override
    public boolean addHabit(String name, String frequency, String category) {
        Habit h = new Habit(name, frequency, category);
        return habitDao.addHabit(h);
    }

    @Override
    public List<Habit> getAllHabits() {
        return habitDao.getAllHabits();
    }

    @Override
    public Habit getHabitById(int id) {
        return habitDao.getHabitById(id);
    }

    @Override
    public boolean deleteHabit(int id) {
        return habitDao.deleteHabit(id);
    }

    @Override
    public boolean updateHabit(int id, String newName, String newFrequency, String newCategory) {
        return habitDao.updateHabit(id, newName, newFrequency, newCategory);
    }
}
