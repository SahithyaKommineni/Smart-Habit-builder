package com.smarthabitbuilder.ui;

import com.smarthabitbuilder.model.Habit;
import com.smarthabitbuilder.model.HabitLog;
import com.smarthabitbuilder.service.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CLI {

    private final HabitService habitService;
    private final HabitLogService habitLogService;
    private final AnalyticsService analyticsService;
    private final Scanner scanner = new Scanner(System.in);

    public CLI(HabitService habitService, HabitLogService habitLogService, AnalyticsService analyticsService) {
        this.habitService = habitService;
        this.habitLogService = habitLogService;
        this.analyticsService = analyticsService;
    }

    public void start() {
        while (true) {
            System.out.println("\n===== SMART HABIT BUILDER =====");
            System.out.println("1. Add Habit");
            System.out.println("2. View All Habits");
            System.out.println("3. Mark Habit Completed");
            System.out.println("4. View Habit Logs");
            System.out.println("5. Analytics");
            System.out.println("6. Update Habit");
            System.out.println("7. Delete Habit");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> addHabit();
                case 2 -> viewHabits();
                case 3 -> markCompleted();
                case 4 -> viewLogs();
                case 5 -> showAnalytics();
                case 6 -> updateHabit();
                case 7 -> deleteHabit();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void addHabit() {
        System.out.print("Enter habit name: ");
        String name = scanner.nextLine();

        System.out.print("Enter frequency (Daily/Weekly): ");
        String freq = scanner.nextLine();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        if (habitService.addHabit(name, freq, category)) {
            System.out.println("Habit added successfully!");
        } else {
            System.out.println("Failed to add habit.");
        }
    }

    private void viewHabits() {
        List<Habit> list = habitService.getAllHabits();

        if (list.isEmpty()) {
            System.out.println("No habits found.");
            return;
        }

        System.out.println("\n--- HABITS ---");
        for (Habit h : list) {
            System.out.println(h.getId() + ". " + h.getName() + " [" + h.getFrequency() + "] - " + h.getCategory());
        }
    }

    private void markCompleted() {
        viewHabits();

        System.out.print("\nEnter Habit ID to mark completed: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean status = habitLogService.markCompleted(id, LocalDate.now());
        System.out.println(status ? "Marked as completed!" : "Failed.");
    }

    private void viewLogs() {
        System.out.print("Enter Habit ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        List<HabitLog> logs = habitLogService.getLogsByHabit(id);

        if (logs.isEmpty()) {
            System.out.println("No logs found.");
            return;
        }

        System.out.println("\n--- LOGS ---");
        for (HabitLog log : logs) {
            System.out.println(log.getDate() + " -> " + log.getStatus());
        }
    }

    private void showAnalytics() {
        System.out.print("Enter Habit ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        List<HabitLog> logs = habitLogService.getLogsByHabit(id);

        double rate = analyticsService.getCompletionRate(logs);
        int streak = analyticsService.getLongestStreak(logs);
        int weekly = analyticsService.getWeeklyCompletion(logs);
        int monthly = analyticsService.getMonthlyCompletion(logs);

        System.out.println("\n--- ANALYTICS ---");
        System.out.println("Completion Rate: " + rate + "%");
        System.out.println("Longest Streak: " + streak + " days");
        System.out.println("Last 7 Days Completed: " + weekly);
        System.out.println("Last 30 Days Completed: " + monthly);
    }

    private void updateHabit() {
        viewHabits();
        System.out.print("\nEnter Habit ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new frequency: ");
        String freq = scanner.nextLine();

        System.out.print("Enter new category: ");
        String category = scanner.nextLine();

        boolean updated = habitService.updateHabit(id, name, freq, category);
        System.out.println(updated ? "Habit updated!" : "Failed to update habit.");
    }

    private void deleteHabit() {
        viewHabits();
        System.out.print("\nEnter Habit ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean deleted = habitService.deleteHabit(id);
        System.out.println(deleted ? "Habit deleted!" : "Failed to delete habit.");
    }
}
