package lesson8.view;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("component")
public class ComponentLayout extends VerticalLayout {
    public ComponentLayout() {
        VerticalLayout verticalLayout = new VerticalLayout();

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Label label1 = new Label("Label1");
        Label label2 = new Label("Label2");
        horizontalLayout.add(label1, label2);

        Label label3 = new Label("Label3");

        verticalLayout.add(horizontalLayout, label3);
        add(verticalLayout);
    }
}
