package com.core.listeners; /**
 * Created by i.vartanian on 18.12.2014.
 */

import com.core.model.entity.User;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.TreeSet;

public class ListenerSession implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        User user = (User)session.getAttribute("user");
        TreeSet<User> users = (TreeSet<User>) session.getServletContext().getAttribute("users");
        users.remove(user);
    }

}
