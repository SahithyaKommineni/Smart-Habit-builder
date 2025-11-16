package com.smarthabitbuilder.service;

import com.smarthabitbuilder.model.Habit;
import java.util.List;

public interface HabitService {

    boolean addHabit(String name, String frequency, String category);

    boolean updateHabit(int id, String newName, String newFrequency, String newCategory);

    boolean deleteHabit(int id);

    List<Habit> getAllHabits();

    Habit getHabitById(int id);
}
