package servlet.user;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (DataBase.INSTANCE.login(login, password)){
            resp.getWriter().println(String.format("Hello %s", DataBase.INSTANCE.getUserByLogin(login).getFirstName()));
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.getWriter().println("Login failed");
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }

        resp.setContentType("text/html;charset=utf-8");
    }
}
