package com.smarthabitbuilder.dao.impl;

import com.smarthabitbuilder.dao.HabitLogDao;
import com.smarthabitbuilder.model.HabitLog;
import com.smarthabitbuilder.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HabitLogDaoImpl implements HabitLogDao {

    public HabitLogDaoImpl() {
        createTable();
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS habit_logs (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "habit_id INTEGER, date TEXT, status TEXT, " +
                "FOREIGN KEY(habit_id) REFERENCES habits(id))";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean markCompleted(int habitId, LocalDate date) {

        String check = "SELECT id FROM habit_logs WHERE habit_id = ? AND date = ?";
        String insert = "INSERT INTO habit_logs(habit_id, date, status) VALUES(?, ?, ?)";

        try (Connection conn = DBUtil.getConnection()) {

            // check existing log for habit + date
            try (PreparedStatement ps = conn.prepareStatement(check)) {
                ps.setInt(1, habitId);
                ps.setString(2, date.toString());

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        String update = "UPDATE habit_logs SET status = ? WHERE id = ?";
                        try (PreparedStatement ups = conn.prepareStatement(update)) {
                            ups.setString(1, "COMPLETED");
                            ups.setInt(2, rs.getInt("id"));
                            ups.executeUpdate();
                            return true;
                        }
                    }
                }
            }

            // insert new
            try (PreparedStatement ins = conn.prepareStatement(insert)) {
                ins.setInt(1, habitId);
                ins.setString(2, date.toString());
                ins.setString(3, "COMPLETED");
                ins.executeUpdate();
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<HabitLog> getLogsByHabit(int habitId) {
        List<HabitLog> list = new ArrayList<>();
        String sql = "SELECT * FROM habit_logs WHERE habit_id = ? ORDER BY date";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, habitId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new HabitLog(
                            rs.getInt("id"),
                            rs.getInt("habit_id"),
                            LocalDate.parse(rs.getString("date")),
                            rs.getString("status")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<HabitLog> getLogsBetween(int habitId, LocalDate start, LocalDate end) {
        List<HabitLog> list = new ArrayList<>();

        String sql = "SELECT * FROM habit_logs WHERE date BETWEEN ? AND ? ORDER BY date";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, start.toString());
            ps.setString(2, end.toString());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new HabitLog(
                            rs.getInt("id"),
                            rs.getInt("habit_id"),
                            LocalDate.parse(rs.getString("date")),
                            rs.getString("status")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
