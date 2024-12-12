import org.junit.jupiter.api.*;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;



public class SubscriberTest {
    private Subscriber subscriber;


    @BeforeEach
    public void setup() {
        subscriber = new Subscriber("TestChannel");
    }

    @Test
    public void testDisplayMessage_whenSubscribed() {
        subscriber.subscribed = true;  // Directly set to subscribed for testing
        subscriber.displayMessage("Test Message");
        // Assert that the message area contains the message
        assertTrue(subscriber.messageArea.getText().contains("Test Message"));
    }

    @Test
    public void testDisplayMessage_whenUnsubscribed() {
        subscriber.subscribed = false;
        subscriber.displayMessage("Test message");
        // Assert that no message is shown
        assertFalse(subscriber.messageArea.getText().contains("Test message"));
    }

    @Test
    public void testDisplayMessage_whenSubscribed_showCorrectMessage() {
        subscriber.subscribed = true;
        subscriber.displayMessage("Test message");
        // Assert that message is equal
        assertFalse(subscriber.messageArea.getText().contains("Test message extended"));
        assertTrue(subscriber.messageArea.getText().contains("Test message"));
    }

    @Test
    public void testDisplayMessage_defaultText() {
        assertTrue(subscriber.messageArea.getText().contains("You are currently not subscribed. Please subscribe to receive messages."));
    }

    @Test
    public void testDisplayMessage_defaultText_whenClickedSubscribeButton() {
        JButton subButton = subscriber.getSubscribeButton();
        subButton.doClick();
        assertTrue(subscriber.messageArea.getText().contains("Subscribed. You will now receive messages."));
    }

    @Test
    public void testDisplayMessage_defaultText_whenClickedUnsubscribeButton() {
        JButton unsubButton = subscriber.getUnsubscribeButton();
        unsubButton.doClick();
        assertTrue(subscriber.messageArea.getText().contains("Unsubscribed. You will no longer receive messages."));
    }

}
