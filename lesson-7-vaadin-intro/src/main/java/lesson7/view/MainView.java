package lesson7.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * Главная страница
 */
@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        add(new Label("This is Vaadin website"));

        add(new Button("Click me", e -> Notification.show("Hello, Spring+Vaadin user!")));
        add(new Button("Login", e -> UI.getCurrent().navigate(LoginView.class)));
        add(new Button("LoginDialog", e -> getLoginDialog(false).open()));
        add(new Button("ModalLoginDialog", e -> getLoginDialog(true).open()));

        setAlignItems(Alignment.CENTER);
    }

    private static Dialog getLoginDialog(boolean modal){
        Dialog dialog = new Dialog();
        dialog.setModal(modal);
        if (modal) {
            dialog.add(new Text("Close me with the esc-key or an outside click"));
        }
        else {
            dialog.add(new Text("Close me with the close click"));
            dialog.add(new Button("Close", e -> dialog.close()));
        }

        dialog.add(new LoginView());

        //dialog.setWidth("400px");
        //dialog.setHeight("150px");
        dialog.setHeightFull();
        dialog.setWidthFull();

        dialog.setResizable(true);
        return dialog;
    }
}