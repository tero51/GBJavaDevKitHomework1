package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame {
    private JButton startButton;
    private JButton stopButton;
    private JTextArea logArea;
    boolean isServerWorking = false;
    private java.util.List<ClientGUI> clients = new ArrayList<ClientGUI>();
    private java.util.List<String> chatHistory = new ArrayList<>();

    public ServerWindow() {
        setTitle("Server Control");
        setSize(450, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

        startButton = new JButton("Start Server");
        stopButton = new JButton("Stop Server");

        logArea = new JTextArea(15, 30);
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

        JPanel panTop = new JPanel(new GridLayout(1, 2));
        panTop.add(startButton);
        panTop.add(stopButton);
        this.add(panTop, "South");

        panel.add(new JScrollPane(logArea));

        add(panel);
        setVisible(true);
    }

    public void logMessage(String message) {
        logArea.append(message + "\n");
    }

    public void registerClient(ClientGUI client) {
        if (isServerWorking) {
            clients.add(client);
            logArea.append("Client registered: " + client.getLogin() + "\n");
        }
    }

    public void broadcastMessage(String message) {
        chatHistory.add(message);
        for (ClientGUI client : clients) {
            client.updateChat(message);
        }
        logArea.append("Broadcasted: " + message + "\n");
    }

    public boolean isServerWorking() {
        return isServerWorking;
    }

    public List<String> getChatHistory() {
        return chatHistory;
    }
}
