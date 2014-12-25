package com.core.model.beans;

import com.core.model.entity.Massages;
import com.core.model.entity.User;

import javax.ejb.Local;
import java.util.List;


/**
 * Created by i.vartanian on 14.12.2014.
 */
@Local
public interface ForumWork {

    public User getUser(String login, String password) throws Exception;

    public User setAndGetUser(String login, String password, String name, String email) throws Exception;

    public Massages persistMassages(Massages massages);

    public Object queryByRange(String stmt, int firstResult, int maxResult);

    public List<Massages> getTopTen();

}
