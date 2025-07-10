package org.example.utils;

import org.example.model.Task;

import javax.swing.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderService {
    public void scheduleReminder(Task task) {
        long delay = Duration.between(LocalDateTime.now(), task.getDeadline()).toMillis();
        if (delay > 0) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    JOptionPane.showMessageDialog(null,
                            "Reminder: Task \"" + task.getTitle() + "\" is due!",
                            "Reminder", JOptionPane.INFORMATION_MESSAGE);
                }
            }, delay);
        }
    }
}
