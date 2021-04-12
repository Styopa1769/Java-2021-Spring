package lesson8.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * Главная страница
 */
@Route("")
public class MainView extends VerticalLayout {

    public MainView() {
        add(new Label("This is Vaadin website"));

        add(new Button("Chart", e -> UI.getCurrent().navigate(Chart.class)));
        add(new Button("Bubble", e -> UI.getCurrent().navigate(Bubble.class)));

        setAlignItems(Alignment.CENTER);
    }
}