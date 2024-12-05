import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class UserChannel{
    private String name;
    private List<Subscriber> subscribers = new ArrayList<>();  // List of subscribers

    // Method to show messages in the text area of the user channel
    private static void showMessage(JTextField messageInputField, JTextArea messageDisplayArea, UserChannel userChannel, String channelName) {
        try {
            String message = messageInputField.getText();
            if (!message.trim().isEmpty() && !message.equals("Type Your Message Here")) {
                messageDisplayArea.append(message + "\n");
                messageInputField.setText("");  // Clear input field after message is sent
                messageInputField.setCaretColor(Color.WHITE);

                // todo: Call the method here to send the messages to subscribers
            }
        } catch (Exception e) {
            System.err.println("Error When Displaying the Message in User Channel");
        }
    }

    public static void main(String[] args) {
        UserChannel userChannel = new UserChannel();
        userChannel.setName("");

        // Create frame
        JFrame frame = new JFrame(userChannel.getName() + "User Channel");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create text area at the top (display messages)
        JTextArea messageDisplayArea = new JTextArea();
        messageDisplayArea.setEditable(false);
        messageDisplayArea.setFocusable(false);
        messageDisplayArea.setLineWrap(true);
        messageDisplayArea.setWrapStyleWord(true);

        // Create a custom TitledBorder for the title
        TitledBorder titledBorder = BorderFactory.createTitledBorder(userChannel.getName() + "User Channel");
        titledBorder.setTitleFont(new Font("SansSerif", Font.BOLD, 16));  // Set custom font size to 16
        messageDisplayArea.setBorder(titledBorder);
        messageDisplayArea.setFont(new Font("SansSerif", Font.PLAIN, 14));

        // Create a scroll pane
        JScrollPane scrollPane = new JScrollPane(messageDisplayArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  // Disable horizontal scrolling

        // Add scroll pane to the center of the frame
        frame.add(scrollPane, BorderLayout.CENTER);

        // Create text field for typing messages
        JTextField messageInputField = new JTextField("Type Your Message Here");
        messageInputField.setForeground(Color.GRAY);
        messageInputField.setCaretColor(Color.WHITE);

        // Add focus listener for placeholder
        messageInputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Clear the placeholder text when clicked and show the cursor
                if (messageInputField.getText().equals("Type Your Message Here")) {
                    messageInputField.setText("");
                    messageInputField.setForeground(Color.BLACK);
                }
                messageInputField.setCaretColor(Color.BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Show the placeholder again if no text is entered and hide the cursor
                if (messageInputField.getText().isEmpty()) {
                    messageInputField.setText("Type Your Message Here");
                    messageInputField.setForeground(Color.GRAY);
                    messageInputField.setCaretColor(Color.WHITE);
                }
            }
        });

        // Create Send button
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(e -> showMessage(messageInputField, messageDisplayArea, userChannel, userChannel.name));

        // Add KeyListener to the message input field to send message on pressing Enter
        messageInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    showMessage(messageInputField, messageDisplayArea, userChannel, userChannel.name);
                }
            }
        });

        // Create panel at the bottom for input field and button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(messageInputField, BorderLayout.CENTER);
        bottomPanel.add(sendButton, BorderLayout.EAST);

        // Add bottom panel to frame
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Set frame to visible
        frame.setVisible(true);

        // First Subscriber
        Subscriber subscriber1 = new Subscriber(userChannel.name);
        userChannel.addSubscriber(subscriber1);
        // Second Subscriber
        Subscriber subscriber2 = new Subscriber(userChannel.name);
        userChannel.addSubscriber(subscriber2);
    }

    public String getName() {
        // todo: code to return the name
        return "";
    }

    public void setName(String name) {
        this.name = name;
    }

    // Add a subscriber to the list
    public void addSubscriber(Subscriber subscriber) {
        // todo: code to add subscribers to subscriber list
    }

    // Return Subscribers
    public List<Subscriber> getSubscribers() {
        // todo: code to rerun the subscriber list
        return null;
    }
}