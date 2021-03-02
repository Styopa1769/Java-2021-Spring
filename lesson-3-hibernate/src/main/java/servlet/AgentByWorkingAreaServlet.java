package servlet;

import entity.Agent;
import json.JsonHelper;
import repo.AgentRepository;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String workingArea = req.getParameter("working_area");

        List<Agent> agents = agentRepository.getAgentsByWorkingArea(workingArea);
        if (agents != null){
            resp.getWriter().println(JsonHelper.toJsonArray(agents));
        } else {
            resp.getWriter().println("Agents not found");
        }

        resp.setContentType("application/json;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
