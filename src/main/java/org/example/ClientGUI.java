package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private JTextField loginField;
    private JTextField passwordField;
    private JTextField serverIPField;
    private JTextField serverPortField;
    private JTextField messageField;
    private JTextArea chatArea;
    private JList<String> userList;
    private JButton connectButton;
    private JButton sendButton;

    public ClientGUI(ServerWindow serverWindow) {
        setTitle("Chat Client");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        loginField = new JTextField(20);
        passwordField = new JTextField(20);
        serverIPField = new JTextField(20);
        serverPortField = new JTextField(20);
        messageField = new JTextField(20);
        chatArea = new JTextArea(10, 40);
        chatArea.setEditable(false);
        userList = new JList<>(new String[]{"User1", "User2", "User3"});
        connectButton = new JButton("Connect");
        sendButton = new JButton("Send");

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Логика подключения к серверу
                serverWindow.logArea.append("Client connected.\n");
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();
                chatArea.append("You: " + message + "\n");
                serverWindow.logArea.append("Message sent: " + message + "\n");
                messageField.setText("");
            }
        });

        topPanel.add(new JLabel("Login:"));
        topPanel.add(loginField);
        topPanel.add(new JLabel("Password:"));
        topPanel.add(passwordField);
        topPanel.add(new JLabel("Server IP:"));
        topPanel.add(serverIPField);
        topPanel.add(new JLabel("Server Port:"));
        topPanel.add(serverPortField);
        topPanel.add(connectButton);

        bottomPanel.add(new JScrollPane(chatArea));
        bottomPanel.add(messageField);
        bottomPanel.add(sendButton);
        bottomPanel.add(new JScrollPane(userList));

        add(topPanel, "North");
        add(bottomPanel, "South");

        setVisible(true);
    }
}