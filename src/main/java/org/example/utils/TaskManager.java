package org.example.utils;

import org.example.model.Task;

import java.time.LocalDate;
import java.util.PriorityQueue;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private PriorityQueue<Task> taskQueue = new PriorityQueue<>();

    public void addTask(Task task) {
        taskQueue.offer(task);
    }

    public void addSampleTasks() {
        addTask(new Task("Submit assignment", 1, java.time.LocalDateTime.now().plusHours(2)));
        addTask(new Task("Buy groceries", 2, java.time.LocalDateTime.now().plusDays(1)));
        addTask(new Task("Call friend", 3, java.time.LocalDateTime.now().plusMinutes(30)));
    }

    public PriorityQueue<Task> getAllTasks() {
        return new PriorityQueue<>(taskQueue);
    }

    public void clearAllTasks() {
        taskQueue.clear();
    }

    public List<Task> getTasksDueToday() {
        LocalDate today = LocalDate.now();
        return taskQueue.stream()
                .filter(t -> t.getDeadline().toLocalDate().equals(today))
                .collect(Collectors.toList());
    }

    public List<Task> getHighPriorityTasks() {
        return taskQueue.stream()
                .filter(t -> t.getPriority() == 1)
                .collect(Collectors.toList());
    }
}
