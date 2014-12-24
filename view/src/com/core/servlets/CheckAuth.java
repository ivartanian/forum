package com.core.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by i.vartanian on 09.12.2014.
 */
public class CheckAuth extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        Object attribut = session.getAttribute("user");
        if (attribut == null){
            request.getRequestDispatcher("auth.html").forward(request, response);
            return;
        } else {
//            request.getRequestDispatcher("info.jsp").forward(request, response);
            response.sendRedirect("info.jsp");
        }

    }

}
