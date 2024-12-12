import javax.swing.*;
import java.util.List;

public interface IUserChannel {
    String getName();
    void setName(String name);
    void sendMessage(String channelName, String message);
    void addSubscriber(Subscriber subscriber);
    List<Subscriber> getSubscribers();
}
