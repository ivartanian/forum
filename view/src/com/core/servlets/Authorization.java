package com.core.servlets;

import com.core.model.beans.ForumWork;
import com.core.model.entity.User;
import com.core.util.Utilities;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by i.vartanian on 10.12.2014.
 */
public class Authorization extends HttpServlet {

    private static final String CONTENT_TYPE = "text/html; charset = utf-8";

    @EJB
    private ForumWork facade;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        getServletContext().setAttribute("users", new TreeSet<User>());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");


        if (login == null | password == null) {
            Utilities.redirectError(response);
            return;
        }

        try {
            password = Utilities.mdGenerate(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            Utilities.redirectError(response);
            return;
        }

        User user = null;
        try {
            if (request.getParameter("auth") != null) {
                user = facade.getUser(login, password);
            } else {
                user = facade.setAndGetUser(login, password, name, email);
            }

            TreeSet<User> users = (TreeSet<User>) getServletContext().getAttribute("users");
            if (users.contains(user)){
                throw new Exception("There are two users in the forum");
            }
            users.add(user);
            Utilities.setUserAttribute(request, user);

        } catch (Exception e) {
            e.printStackTrace();
            Utilities.redirectError(response);
            return;
        }

        request.getRequestDispatcher("begin").forward(request, response);

    }

}
