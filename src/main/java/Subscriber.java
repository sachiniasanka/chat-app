import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Subscriber implements ISubscriber {
    JTextArea messageArea;
    boolean subscribed = false;
    // Create buttons for subscribing and unsubscribing
    JButton subscribeButton = new JButton("Subscribe");
    JButton unsubscribeButton = new JButton("Unsubscribe");
    private JFrame frame;

    public Subscriber(String channelName) {
        try {
            // Create the subscriber frame
            frame = new JFrame(channelName + "'s Subscriber");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // Create a text area in the subscriber frame to display received messages
            messageArea = new JTextArea();
            messageArea.setEditable(false);
            messageArea.setLineWrap(true);
            messageArea.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(messageArea);
            frame.add(scrollPane, BorderLayout.CENTER);

            // Add action listener for subscribing
            subscribeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    subscribed = true;
                    messageArea.setText("Subscribed. You will now receive messages.\n");
                }
            });

            // Add action listener for unsubscribing
            unsubscribeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    subscribed = false;
                    messageArea.setText("Unsubscribed. You will no longer receive messages.\n");
                }
            });

            // Create a panel to hold the buttons and add them to it
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(subscribeButton);
            buttonPanel.add(unsubscribeButton);
            frame.add(buttonPanel, BorderLayout.NORTH);

            // Show a message indicating the subscription status on startup
            if (!subscribed) {
                messageArea.setText("You are currently not subscribed. Please subscribe to receive messages.\n");
            }

            // Set the frame visible
            frame.setVisible(true);
        } catch (Exception e) {
            System.err.println("Error initializing subscriber UI");
        }
    }

    @Override
    public void displayMessage(String message) {
        try {
            if (subscribed) {
                if (message != null) {
                    messageArea.append(message + "\n");
                } else {
                    throw new IllegalArgumentException("Message cannot be null.");
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public JButton getSubscribeButton() {
        return subscribeButton;
    }

    public JButton getUnsubscribeButton() {
        return unsubscribeButton;
    }
}
