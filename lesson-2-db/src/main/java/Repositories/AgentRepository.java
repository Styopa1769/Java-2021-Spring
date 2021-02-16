package Repositories;

import DataBase.HibernateManager;
import Entities.Agent;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AgentRepository {
    private final HibernateManager db;

    public AgentRepository() {
        this.db = new HibernateManager();
    }

    public Agent getAgentByCode(String code){
        Session session = db.getSession();
        Transaction transaction = session.beginTransaction();
        Agent agent = session.get(Agent.class, code);
        transaction.commit();
        session.close();
        return agent;
    }

    public List<Agent> getAgentsByWorkingArea(String workingArea) {
        Session session = db.getSession();
        Transaction transaction = session.beginTransaction();
        Query<Agent> query = session.createQuery("from Agent a where a.working_area=:working_area", Agent.class);
        query.setParameter("working_area", workingArea);
        return query.getResultList();
    }
}
