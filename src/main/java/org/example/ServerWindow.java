package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    private JButton startButton;
    private JButton stopButton;
    JTextArea logArea;
    private boolean isServerWorking = false;

    public ServerWindow() {
        setTitle("Server Control");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        startButton = new JButton("Start Server");
        stopButton = new JButton("Stop Server");
        logArea = new JTextArea(10, 30);
        logArea.setEditable(false);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isServerWorking) {
                    isServerWorking = true;
                    logArea.append("Server started.\n");
                } else {
                    logArea.append("Server is already running.\n");
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    isServerWorking = false;
                    logArea.append("Server stopped.\n");
                } else {
                    logArea.append("Server is not running.\n");
                }
            }
        });

        panel.add(startButton);
        panel.add(stopButton);
        panel.add(new JScrollPane(logArea));

        add(panel);
        setVisible(true);
    }
}
