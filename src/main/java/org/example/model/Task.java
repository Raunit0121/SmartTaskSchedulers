package org.example.model;

import java.time.LocalDateTime;

public class Task implements Comparable<Task> {
    private String title;
    private int priority;
    private LocalDateTime deadline;

    public Task(String title, int priority, LocalDateTime deadline) {
        this.title = title;
        this.priority = priority;
        this.deadline = deadline;
    }

    public String getTitle() { return title; }
    public int getPriority() { return priority; }
    public LocalDateTime getDeadline() { return deadline; }

    @Override
    public int compareTo(Task other) {
        if (this.priority != other.priority)
            return Integer.compare(this.priority, other.priority);
        return this.deadline.compareTo(other.deadline);
    }
}
