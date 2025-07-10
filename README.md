# 🧠 Smart Task Scheduler

A simple yet powerful desktop-based task manager built in Java using Swing, PriorityQueue, JSON storage, and reminder popups.

---

## 📌 Features

- ✅ Add tasks with title, priority, and deadline
- ✅ Tasks automatically sorted by priority and urgency
- ✅ Real-time popup reminders
- ✅ Save and load tasks to/from JSON
- ✅ Simple and intuitive Swing-based GUI
- ✅ Filters (Today’s tasks, High-priority - coming soon)

---

## 📁 Project Structure

     SmartTaskScheduler/
    └── src/
        └── main/
            ├── java/
            │   └── org/example/
            │       ├── Main.java
            │       ├── model/
            │       │   └── Task.java
            │       ├── storage/
            │       │   └── TaskStorage.java
            │       ├── ui/
            │       │   ├── MainFrame.java
            │       │   └── TaskPanel.java
            │       └── utils/
            │           ├── ReminderService.java
            │           └── TaskManager.java
            └── resources/
                └── tasks.json

---

## 🛠️ Technologies Used

- Java 17
- Swing (for GUI)
- Gson (for JSON read/write)
- IntelliJ IDEA (recommended IDE)
- Maven (for dependency management)

---

## 🚀 How to Run

### 🖥️ From IntelliJ IDEA:

1. Clone the repo:
   ```bash
   git clone https://github.com/yourusername/SmartTaskScheduler.git
   cd SmartTaskScheduler

2. Open in IntelliJ and wait for Maven to resolve dependencies.

3. Run the Main.java class from org.example.

### 💻 From Command Line:

Make sure Java 17 and Maven are installed.

    # Compile
    mvn clean compile
    
    # Run the application
    mvn exec:java -Dexec.mainClass="org.example.Main"

    

## 🔧 Optional: Add exec-maven-plugin to your pom.xml for CLI support.

---

### 📝 JSON File Format (tasks.json)
  
    [
      {
        "title": "Finish Java Project",
        "priority": 1,
        "deadline": "2025-07-11T14:00"
      }
    ]


---


## 🎨 Screenshots

![Screenshot 2025-07-10 151751](https://github.com/user-attachments/assets/32163daa-9287-477a-8cfe-cd6da77a27c2)


---

### 📦 Future Improvements

 Edit and delete task functionality
 Filters (by today, by priority)
 Tray icon reminders
 Notifications with sound


### 🧑‍💻 Author

Raunit Goyal 
GitHub @Raunit0121
## About Me

Check out my [GitHub Profile](https://github.com/Raunit0121) for more projects and contributions.

