package lesson9.vaadin.view;

import com.vaadin.componentfactory.Chat;
import com.vaadin.componentfactory.model.Message;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import lesson9.vaadin.db.mock.User;

import java.util.List;
import java.util.stream.Collectors;

@Route("chat")
public class ChatView extends VerticalLayout {

    public ChatView() {
        Chat chat = new Chat();
        chat.setMessages(loadMessages());
        chat.setDebouncePeriod(200);
        chat.setLazyLoadTriggerOffset(2500);
        chat.scrollToBottom();

        chat.addChatNewMessageListener(event -> {
            event.getSource().addNewMessage(createMessageForUser(event.getMessage(), getCurrentUser()));
            event.getSource().clearInput();
            event.getSource().scrollToBottom();
        });

        add(chat);
    }

    private static List<Message> loadMessages(){
        return User.USERS_BY_NAME.values()
                .stream()
                .map(user -> createMessageForUser("Hello", user))
                .collect(Collectors.toList());
    }

    private static Message createMessageForUser(String message, User user){
        return new Message(message, user.getPicture(), user.getName(), user.equals(getCurrentUser()));
    }

    private static User getCurrentUser(){
        UserSession userSession = VaadinSession.getCurrent().getAttribute(UserSession.class);
        return User.USERS_BY_NAME.getOrDefault(userSession.getName(), User.GUEST);
    }
}
