package servlet;

import entity.Agent;
import repo.AgentRepository;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = req.getParameter("agent_code");

        Agent agent = agentRepository.getAgentByCode(code);
        if (agent != null){
            resp.getWriter().println(agent.toJson());
        } else {
            resp.getWriter().println("Entities.Agent not found");
        }

        resp.setContentType("application/json;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

}
