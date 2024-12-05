import org.junit.jupiter.api.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UserChannelTest {
    private UserChannel userChannel;

    @BeforeEach
    public void setup() {
        userChannel = new UserChannel();
    }

    @Test
    public void testChannelName() {
        userChannel.setName("Sachini");
        String channelName = userChannel.getName();
        assertEquals("Sachini", channelName);
    }

    @Test
    public void testSubscribersCount() {
        Subscriber testSubscriber1 = new Subscriber("Test Subscriber 1");
        userChannel.addSubscriber(testSubscriber1);
        List<Subscriber> subscriberList = userChannel.getSubscribers();
        assertEquals(1, subscriberList.size());

        Subscriber testSubscriber2 = new Subscriber("Test Subscriber 2");
        userChannel.addSubscriber(testSubscriber2);
        subscriberList = userChannel.getSubscribers();
        assertEquals(2, subscriberList.size());
    }
}
