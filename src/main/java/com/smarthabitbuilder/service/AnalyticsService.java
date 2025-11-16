package com.smarthabitbuilder.service;

import com.smarthabitbuilder.model.HabitLog;
import java.util.List;

public interface AnalyticsService {

    // % completed for one habit
    double getCompletionRate(List<HabitLog> logs);

    // Longest continuous streak for a habit
    int getLongestStreak(List<HabitLog> logs);

    // How many days user completed in last 7 days
    int getWeeklyCompletion(List<HabitLog> logs);

    // How many days user completed in last 30 days
    int getMonthlyCompletion(List<HabitLog> logs);
}
