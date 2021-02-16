package Servlets;

import Entities.Agent;
import Repositories.AgentRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AgentByWorkingAreaServlet extends HttpServlet {
    private final AgentRepository agentRepository;

    public AgentByWorkingAreaServlet(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String workingArea = req.getParameter("working_area");

        List<Agent> agents = agentRepository.getAgentsByWorkingArea(workingArea);
        if (agents != null){
            resp.getWriter().println(agents);
        } else {
            resp.getWriter().println("Agents not found");
        }

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
