package org.example.ui;

import org.example.model.Task;
import org.example.utils.ReminderService;
import org.example.utils.TaskManager;
import org.example.storage.TaskStorage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskPanel extends JPanel {
    private TaskManager taskManager = new TaskManager();
    private JTable taskTable;
    private DefaultTableModel tableModel;

    private JTextField titleField;
    private JComboBox<String> priorityBox;
    private JTextField deadlineField;

    public TaskPanel() {
        setLayout(new BorderLayout());
        taskManager.addSampleTasks();

        // Table
        tableModel = new DefaultTableModel(new Object[]{"Title", "Priority", "Deadline"}, 0);
        taskTable = new JTable(tableModel);
        add(new JScrollPane(taskTable), BorderLayout.CENTER);

        // Form
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        titleField = new JTextField();
        priorityBox = new JComboBox<>(new String[]{"High", "Medium", "Low"});
        deadlineField = new JTextField("yyyy-MM-dd HH:mm");

        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(e -> addTask());

        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Priority:"));
        formPanel.add(priorityBox);
        formPanel.add(new JLabel("Deadline:"));
        formPanel.add(deadlineField);
        formPanel.add(addButton);

        add(formPanel, BorderLayout.SOUTH);

        // Save/Load
        JPanel topButtons = new JPanel();
        JButton saveBtn = new JButton("Save");
        JButton loadBtn = new JButton("Load");

        saveBtn.addActionListener(e -> {
            try {
                TaskStorage.saveTasks(taskManager.getAllTasks().stream().toList());
                JOptionPane.showMessageDialog(this, "Tasks saved!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Save error: " + ex.getMessage());
            }
        });

        loadBtn.addActionListener(e -> {
            try {
                var loaded = TaskStorage.loadTasks();
                taskManager.clearAllTasks();
                for (Task t : loaded) taskManager.addTask(t);
                refreshTaskTable();
                JOptionPane.showMessageDialog(this, "Tasks loaded!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Load error: " + ex.getMessage());
            }
        });

        topButtons.add(saveBtn);
        topButtons.add(loadBtn);
        add(topButtons, BorderLayout.NORTH);

        refreshTaskTable();
    }

    private void addTask() {
        try {
            String title = titleField.getText();
            int priority = priorityBox.getSelectedIndex() + 1;
            LocalDateTime deadline = LocalDateTime.parse(deadlineField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            Task task = new Task(title, priority, deadline);
            taskManager.addTask(task);
            new Thread(() -> new ReminderService().scheduleReminder(task)).start();

            refreshTaskTable();
            titleField.setText("");
            deadlineField.setText("yyyy-MM-dd HH:mm");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Use format: yyyy-MM-dd HH:mm");
        }
    }

    private void refreshTaskTable() {
        tableModel.setRowCount(0);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (Task task : taskManager.getAllTasks()) {
            tableModel.addRow(new Object[]{
                    task.getTitle(),
                    getPriorityLabel(task.getPriority()),
                    task.getDeadline().format(fmt)
            });
        }
    }

    private String getPriorityLabel(int p) {
        return switch (p) {
            case 1 -> "High";
            case 2 -> "Medium";
            case 3 -> "Low";
            default -> "Unknown";
        };
    }
}
