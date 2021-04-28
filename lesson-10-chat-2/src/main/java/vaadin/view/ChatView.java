package vaadin.view;

import chat.client.Client;
import com.vaadin.componentfactory.Chat;
import com.vaadin.componentfactory.model.Message;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import vaadin.mock.User;

import java.util.List;
import java.util.stream.Collectors;

@Route("chat")
@Push
public class ChatView extends VerticalLayout {

    private final User currentUser;

    public ChatView() {
        UserSession userSession = VaadinSession.getCurrent().getAttribute(UserSession.class);
        currentUser = User.USERS_BY_NAME.getOrDefault(userSession == null ? "" : userSession.getName(), User.GUEST);

        Chat chat = new Chat();
        chat.setMessages(loadMessages());
        chat.setDebouncePeriod(200);
        chat.setLazyLoadTriggerOffset(2500);
        chat.scrollToBottom();

        try {
            Client client = new ChatClient("localhost", 8080, chat, UI.getCurrent());
            chat.addChatNewMessageListener(event -> {
                event.getSource().addNewMessage(createMessageForUser(event.getMessage(), currentUser));
                event.getSource().clearInput();
                event.getSource().scrollToBottom();
                client.writeMessage(createMessageDto(event.getMessage(), currentUser));
            });
        } catch (Exception e) {
            throw new RuntimeException("Failed to create client");
        }

        add(chat);
    }

    private List<Message> loadMessages(){
        return User.USERS_BY_NAME.values()
                .stream()
                .map(user -> createMessageForUser("Hello", user))
                .collect(Collectors.toList());
    }

    private Message createMessageForUser(String message, User user){
        return new Message(message, user.getPicture(), user.getName(), user.equals(currentUser));
    }

    private static String createMessageDto(String message, User user)
    {
        return user.getName() + ":" + message;
    }

    private final class ChatClient extends Client{
        final Chat chat;
        final UI currentUI;

        public ChatClient(String host, int port, Chat chat, UI ui) throws Exception {
            super(host, port);
            this.chat = chat;
            this.currentUI = ui;
        }

        @Override
        public void onMessage(String message) {
            currentUI.access(() -> chat.addNewMessage(getMessageFromDto(message)));
        }

        private Message getMessageFromDto(String dto)
        {
            String[] splitted = dto.split(":");
            return createMessageForUser(splitted[1], User.USERS_BY_NAME.getOrDefault(splitted[0], User.GUEST));
        }
    }
}
