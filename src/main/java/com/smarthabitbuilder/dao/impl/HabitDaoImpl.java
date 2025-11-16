package com.smarthabitbuilder.dao.impl;

import com.smarthabitbuilder.dao.HabitDao;
import com.smarthabitbuilder.model.Habit;
import com.smarthabitbuilder.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class HabitDaoImpl implements HabitDao {

    public HabitDaoImpl() {
        createTable();
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS habits (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, frequency TEXT, category TEXT)";
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addHabit(Habit habit) {
        String sql = "INSERT INTO habits(name, frequency, category) VALUES(?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, habit.getName());
            ps.setString(2, habit.getFrequency());
            ps.setString(3, habit.getCategory());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Habit> getAllHabits() {
        List<Habit> list = new ArrayList<>();
        String sql = "SELECT * FROM habits";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Habit(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("frequency"),
                        rs.getString("category")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
