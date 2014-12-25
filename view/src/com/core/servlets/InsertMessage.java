package com.core.servlets;

import com.core.model.beans.ForumWork;
import com.core.model.entity.Massages;
import com.core.model.entity.User;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by i.vartanian on 23.12.2014.
 */
public class InsertMessage extends HttpServlet {

    @EJB
    private ForumWork f;
    private SimpleDateFormat dateFormat;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dateFormat = new SimpleDateFormat();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String text = null;
        text = request.getParameter("text");
        Massages massages = new Massages();
        massages.setLogin(user.getLogin());
        massages.setMassage(text);

        String currentDate = dateFormat.format(new Date());
        massages.setDate(currentDate);

//        massages.setId(UUID.randomUUID().toString());
        f.persistMassages(massages);
        request.getRequestDispatcher("begin").forward(request, response);

    }

}
