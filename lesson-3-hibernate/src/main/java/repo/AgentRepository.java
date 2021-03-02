package repo;

import db.hibernate.HibernateManager;
import entity.Agent;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AgentRepository {

    public AgentRepository() {
    }

    public Agent getAgentByCode(String code){
        Session session = HibernateManager.getSession();
        Agent agent = session.get(Agent.class, code);
        session.close();
        return agent;
    }

    public List<Agent> getAgentsByWorkingArea(String workingArea) {
        Session session = HibernateManager.getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Agent> criteriaQuery = criteriaBuilder.createQuery(Agent.class);
        Root<Agent> root = criteriaQuery.from(Agent.class);
        criteriaQuery = criteriaQuery.where(criteriaBuilder.equal(root.get("workingArea"), workingArea));

        Query<Agent> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
