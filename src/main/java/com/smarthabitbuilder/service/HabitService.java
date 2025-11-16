package com.smarthabitbuilder.service;

import com.smarthabitbuilder.model.Habit;
import java.util.List;

public interface HabitService {

    boolean addHabit(String name, String frequency, String category);

    List<Habit> getAllHabits();

    Habit getHabitById(int id);

    boolean deleteHabit(int id);

    boolean updateHabit(int id, String newName, String newFrequency, String newCategory);
}
