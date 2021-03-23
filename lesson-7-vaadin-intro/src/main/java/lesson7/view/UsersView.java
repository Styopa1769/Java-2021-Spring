package lesson7.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lesson6.api.dto.UserDto;
import lesson7.rest.UserApi;

@Route("users")
public class UsersView extends VerticalLayout {
    private final UserApi userApi = new UserApi();

    public UsersView() {
        add(new Label("All users table"));
        add(getTable());

        setAlignItems(Alignment.CENTER);
    }

    private Grid<UserDto> getTable(){
        Grid<UserDto> table = new Grid<>(UserDto.class);
        table.setItems(userApi.getAll());
        return table;
    }
}
