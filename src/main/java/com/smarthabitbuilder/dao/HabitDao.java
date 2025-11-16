package com.smarthabitbuilder.dao;

import com.smarthabitbuilder.model.Habit;

import java.util.List;

public interface HabitDao {
    boolean addHabit(Habit habit);
    boolean updateHabit(Habit habit);
    boolean deleteHabit(int id);
    List<Habit> getAllHabits();
    Habit getHabitById(int id);

    boolean updateHabit(int id, String newName, String newFrequency, String newCategory);
}
