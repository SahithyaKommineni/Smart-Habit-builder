package com.smarthabitbuilder.service;

import com.smarthabitbuilder.model.HabitLog;

import java.time.LocalDate;
import java.util.List;

public class AnalyticsServiceImpl implements AnalyticsService {

    @Override
    public double getCompletionRate(List<HabitLog> logs) {
        if (logs.isEmpty()) return 0;

        long completed = logs.stream()
                .filter(l -> l.getStatus().equalsIgnoreCase("COMPLETED"))
                .count();

        return (completed * 100.0) / logs.size();
    }

    @Override
    public int getLongestStreak(List<HabitLog> logs) {
        if (logs.isEmpty()) return 0;

        logs.sort((a, b) -> a.getDate().compareTo(b.getDate()));

        int longest = 1;
        int current = 1;

        for (int i = 1; i < logs.size(); i++) {
            LocalDate prev = logs.get(i - 1).getDate();
            LocalDate curr = logs.get(i).getDate();

            if (curr.equals(prev.plusDays(1))) {
                current++;
            } else {
                current = 1;
            }
            longest = Math.max(longest, current);
        }
        return longest;
    }

    @Override
    public int getWeeklyCompletion(List<HabitLog> logs) {
        LocalDate weekAgo = LocalDate.now().minusDays(7);

        return (int) logs.stream()
                .filter(l -> l.getStatus().equalsIgnoreCase("COMPLETED"))
                .filter(l -> !l.getDate().isBefore(weekAgo))
                .count();
    }

    @Override
    public int getMonthlyCompletion(List<HabitLog> logs) {
        LocalDate monthAgo = LocalDate.now().minusDays(30);

        return (int) logs.stream()
                .filter(l -> l.getStatus().equalsIgnoreCase("COMPLETED"))
                .filter(l -> !l.getDate().isBefore(monthAgo))
                .count();
    }
}
