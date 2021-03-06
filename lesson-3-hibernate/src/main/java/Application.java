import repo.AgentRepository;
import servlet.AgentByWorkingAreaServlet;
import servlet.AgentServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.http.HttpServlet;

public class Application {

    public static void main(String[] args) {
        HttpServlet agentServlet = new AgentServlet(new AgentRepository());
        HttpServlet agentByWorkingAreaServlet = new AgentByWorkingAreaServlet(new AgentRepository());

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(agentServlet), "/agent");
        context.addServlet(new ServletHolder(agentByWorkingAreaServlet), "/agents_by_area");

        Server server = new Server(8080);
        server.setHandler(context);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
