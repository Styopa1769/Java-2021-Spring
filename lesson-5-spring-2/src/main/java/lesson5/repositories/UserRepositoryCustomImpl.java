package lesson5.repositories;

import lesson5.dto.UserDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserDto> getAll() {
        List<UserDto> resultList= new ArrayList<>();
        Query query = entityManager.createNativeQuery("select name, id from user_entity","userMapping");
        resultList=query.getResultList();
        return resultList;
    }

    @Override
    public List<UserDto> getAllByName(String name) {
        List<UserDto> resultList= new ArrayList<>();
        Query query = entityManager.createNativeQuery("select name, id from user_entity where name = :name","userMapping");
        query.setParameter("name", name);
        resultList=query.getResultList();
        return resultList;
    }
}
