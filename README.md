# Smart Habit Builder & Productivity Analytics System  
### Pure Core Java + SQLite + CLI Application

The **Smart Habit Builder** is a productivity and habit-tracking system built entirely using **Core Java**, following a clean layered architecture with **DAO**, **Service**, and **Model** layers.  
Data is stored using a lightweight **SQLite** database, and the system runs through a simple **Command Line Interface (CLI)**.

---

## 🚀 Features

### ✔ Habit Management
- Add habits  
- Update habits  
- Delete habits  
- View all habits  

### ✔ Habit Logging
- Mark habits as completed  
- Automatically prevents duplicate logs  
- View logs for selected habits  

### ✔ Productivity Analytics
- Completion percentage  
- Logs between dates  
- Consistency insights  
- Habit performance comparison  

---

## 🏗 Project Structure

src/main/java/com/smarthabitbuilder/

├── model/
│ ├── Habit.java
│ ├── HabitLog.java
│
├── dao/
│ ├── HabitDao.java
│ ├── HabitLogDao.java
│ └── impl/
│ ├── HabitDaoImpl.java
│ ├── HabitLogDaoImpl.java
│
├── service/
│ ├── HabitService.java
│ ├── HabitLogService.java
│ └── impl/
│ ├── HabitServiceImpl.java
│ ├── HabitLogServiceImpl.java
│
├── util/
│ └── DBUtil.java
│
└── ui/
└── CLI.java


---

## 🛢 Database

SQLite database is used (no installation required).  
The driver automatically creates:

### `habits` table
| id | name | frequency | category |

### `habit_logs` table
| id | habit_id | date | status |

Database file: **smart_habits.db**

---

## ⚙️ Technologies Used

- Java 17+  
- SQLite (sqlite-jdbc driver)  
- JDBC  
- DAO Pattern  
- Service Layer Architecture  
- IntelliJ IDEA  

---

## ▶️ How to Run

### 1. Clone the repo
```bash
git clone https://github.com/yourusername/smart-habit-builder.git
cd smart-habit-builder
