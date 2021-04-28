package vaadin.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import io.github.centrifugal.centrifuge.Client;
import io.github.centrifugal.centrifuge.ConnectEvent;
import io.github.centrifugal.centrifuge.DisconnectEvent;
import io.github.centrifugal.centrifuge.EventListener;
import io.github.centrifugal.centrifuge.Options;
import io.github.centrifugal.centrifuge.PublishEvent;
import io.github.centrifugal.centrifuge.SubscribeErrorEvent;
import io.github.centrifugal.centrifuge.SubscribeSuccessEvent;
import io.github.centrifugal.centrifuge.Subscription;
import io.github.centrifugal.centrifuge.SubscriptionEventListener;

import static java.nio.charset.StandardCharsets.UTF_8;

@Route("notification")
@Push
public class NotificationView extends VerticalLayout {
    private static final String KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM3MjIiLCJleHAiOjE2MjAxNjA4MTV9.Fh1_GVrFxlgZ6isNINjzdf8T24qOpr0Bgl2yW5mHPWo";

    public NotificationView() {
        subscribe(UI.getCurrent());
    }

    void subscribe(UI currentUI)
    {
        EventListener listener = new EventListener() {
            @Override
            public void onConnect(Client client, ConnectEvent event) {
                System.out.println("connected");
            }

            @Override
            public void onDisconnect(Client client, DisconnectEvent event) {
                System.out.printf("disconnected %s, reconnect %s%n", event.getReason(), event.getReconnect());
            }
        };

        SubscriptionEventListener subListener = new SubscriptionEventListener() {
            @Override
            public void onSubscribeSuccess(Subscription sub, SubscribeSuccessEvent event) {
                System.out.println("subscribed to " + sub.getChannel());
            }
            @Override
            public void onSubscribeError(Subscription sub, SubscribeErrorEvent event) {
                System.out.println("subscribe error " + sub.getChannel() + " " + event.getMessage());
            }
            @Override
            public void onPublish(Subscription sub, PublishEvent event) {
                String data = new String(event.getData(), UTF_8);
                System.out.println("Receive message: " + data);
                currentUI.access(() -> Notification.show("message from " + sub.getChannel() + " " + data, 0, Notification.Position.TOP_CENTER));
            }
        };

        Client client = new Client(
                "ws://localhost:8000/connection/websocket?format=protobuf",
                new Options(),
                listener);
        client.connect();
        client.setToken(KEY);
        client.newSubscription("channel", subListener).subscribe();
    }
}
