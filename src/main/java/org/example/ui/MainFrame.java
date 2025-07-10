package org.example.ui;

import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Smart Task Scheduler");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new TaskPanel());
    }
}
