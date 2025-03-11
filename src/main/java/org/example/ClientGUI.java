package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
    private ServerWindow serverWindow;
    private String login;

    public ClientGUI(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
        setTitle("Chat Client");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel(new GridLayout(5,1));
        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel(new GridLayout(1,2));
//        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));




        loginField = new JTextField("Login", 25);
        passwordField = new JTextField("Password",25);
        serverIPField = new JTextField("IP" ,25);
        serverPortField = new JTextField("Port",25);
        messageField = new JTextField(25);
        Dimension messageFieldSize = messageField.getPreferredSize();

        Dimension sendButtonSize = new Dimension(
                messageFieldSize.width / 3,
                messageFieldSize.height
        );
        chatArea = new JTextArea(27, 40);
        chatArea.setEditable(false);
        connectButton = new JButton("Connect");
        sendButton = new JButton("Send");
        sendButton.setPreferredSize(sendButtonSize);
        sendButton.setFont(new Font("Arial", Font.PLAIN, 12));


        bottomPanel.setLayout(new BorderLayout());
        JPanel sendButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sendButtonPanel.add(sendButton);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (serverWindow.isServerWorking) {
                    login = loginField.getText();
                    serverWindow.registerClient(ClientGUI.this);
                    chatArea.append("Вы успешно подключились!\n");
                    serverWindow.logMessage("Client connected.");
                    userList = new JList<>(new String[]{loginField.getText()});
                    for(String message : serverWindow.getChatHistory()) {
                        chatArea.append(message + "\n");
                    }
                    topPanel.setVisible(false);
                } else {
                    chatArea.append("Подключение не удалось\n");
                }
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageField.getText();

                if (!message.isEmpty()) {
                    String fullMessage = login + ": " + message;
                    serverWindow.broadcastMessage(fullMessage);
                    messageField.setText("");
                }
            }
        });

//        topPanel.add(new JLabel("Login:"));
        topPanel.add(loginField);
//        topPanel.add(new JLabel("Password:"));
        topPanel.add(passwordField);
//        topPanel.add(new JLabel("Server IP:"));
        topPanel.add(serverIPField);
//        topPanel.add(new JLabel("Server Port:"));
        topPanel.add(serverPortField);
        topPanel.add(connectButton);


        middlePanel.add(new JScrollPane(chatArea));
        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);
//        bottomPanel.add(new JScrollPane(userList));

        add(topPanel, "North");
        add(middlePanel);
        add(bottomPanel, "South");

        setVisible(true);
    }
    public void updateChat(String message) {
        chatArea.append(message + "\n");
    }

    public String getLogin() {
        return login;
    }
}