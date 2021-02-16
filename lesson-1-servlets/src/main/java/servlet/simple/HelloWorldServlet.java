package servlet.simple;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloWorldServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        response.getWriter().println("Hello world");

        response.setContentType("text/html;charset=utf-8");

        response.setStatus(HttpServletResponse.SC_OK);

    }
}
