package Servlets;

import Entities.Agent;
import Repositories.AgentRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AgentServlet extends HttpServlet {
    private final AgentRepository agentRepository;

    public AgentServlet(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("agent_code");

        Agent agent = agentRepository.getAgentByCode(code);
        if (agent != null){
            resp.getWriter().println(agent);
        } else {
            resp.getWriter().println("Entities.Agent not found");
        }

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

}
