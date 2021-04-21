package lesson9.vaadin.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {
    public MainView() {
        add(new Label("This is Vaadin website"));

        add(new Button("Login", e -> UI.getCurrent().navigate(LoginView.class)));

        setAlignItems(FlexComponent.Alignment.CENTER);
    }
}
