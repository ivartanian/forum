package com.core.model.beans;

import com.core.model.entity.Massages;
import com.core.model.entity.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by i.vartanian on 14.12.2014.
 */
@Local(ForumWork.class)
@Stateless(name = "ForumFacadeBean")
public class ForumFacadeBean implements ForumWork {

    @PersistenceContext(unitName = "FacadeUnit")
    private EntityManager entityManager;


    public ForumFacadeBean() {
    }

    @Override
    public User getUser(String login, String password) throws Exception {
        List<User> usersList = findPersonByLoginAndPassword(login, password);
        if (usersList.size() <=0){
            throw new Exception("This user is failed");
        }else if (usersList.size() > 1){
            throw new Exception("The database have more 1 user");
        }
        return usersList.get(0);
    }

    @Override
    public User setAndGetUser(String login, String password, String name, String email) throws Exception {

        List<User> usersList = findPersonByLoginAndEmail(login, email);
        if (usersList.size() >= 1){
            throw new Exception("The database have one or more users");
        }
//        String sql = "INSERT INTO USERS (LOGIN, NAME, PASSWORD, EMAIL) VALUES (?, ?, ?, ?);";
//        Query query = entityManager.createNativeQuery(sql);
//        query.setParameter(1, login);
//        query.setParameter(2, name);
//        query.setParameter(3, password);
//        query.setParameter(4, email);
//        query.executeUpdate();

        User user = new User(login, name, password, email);
        entityManager.persist(user);

        return user;

    }

    public List<User> findPersonByLoginAndPassword(String login, String password) {

        Query query = entityManager.createNamedQuery("findPersonByLoginAndPassword");
        query.setParameter("login", login);
        query.setParameter("password", password);
        return query.getResultList();

    }

    public List<User> findPersonByLoginAndEmail(String login, String email) {

        Query query = entityManager.createNamedQuery("findPersonByLoginAndEmail");
        query.setParameter("login", login);
//        query.setParameter("email", email);
        return query.getResultList();

    }

    @PostConstruct
    public void myInit() {
        System.out.println(">>>>>>>>>>>>>>>>>Bean created");
    }

    @PreDestroy
    public void myDestroy() {
        System.out.println(">>>>>>>>>>>>>>>>>Bean destroy");
    }

    public Massages persistMassages(Massages massages) {
        entityManager.persist(massages);
        return massages;
    }
}
