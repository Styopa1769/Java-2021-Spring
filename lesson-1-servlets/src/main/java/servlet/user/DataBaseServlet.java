package servlet.user;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DataBaseServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        User user = DataBase.INSTANCE.getUserByLogin(login);

        if (user != null){
            response.getWriter().println(user.toString());
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.getWriter().println("User not found");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        response.setContentType("text/html;charset=utf-8");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = new User(firstName,lastName, login, password);
        DataBase.INSTANCE.putUser(user);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
