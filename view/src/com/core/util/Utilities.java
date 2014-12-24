package com.core.util;

import com.core.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by i.vartanian on 10.12.2014.
 */
public class Utilities {

    public static void redirectError(HttpServletResponse response) throws IOException {
        response.sendRedirect("error.html");
    }

    public static String mdGenerate(String password) throws NoSuchAlgorithmException {

        MessageDigest md5 = null;
        md5 = MessageDigest.getInstance("MD5");
        md5.reset();
        md5.update(password.getBytes());
        byte[] digest = md5.digest();

        return new String(digest);

    }

    public static void setUserAttribute(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);
    }


//    public static void authorization(HttpServletRequest request, HttpServletResponse response, User user) throws IOException, SQLException, ServletException {
//
//        String login = user.getLogin();
//        String password = user.getPassword();
//
//        Connection connection = null;
//        Statement statement = null;
//        try {
//
////            connection = DriverManager.getConnection("jdbc:sqlite:C:\\User\\i.vartanian\\Documents\\SQLite_db\\forum\\forum.db");
//
//            InitialContext context = new InitialContext();
//
//            connection = ((DataSource) context.lookup("jdbc/forum")).getConnection();
//
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
//            preparedStatement.setString(1, login);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next() && resultSet.getString("password").equals(password)){
//                setUserAttribute(request, user);
//                response.sendRedirect("html/info.jsp");
//                return;
//            }else {
//                response.sendRedirect("html/auth.html");
//                return;
//            }
//
//        } catch (NamingException e) {
//            e.printStackTrace();
//        } finally {
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//    }
//
//    public static void registration(HttpServletRequest request, HttpServletResponse response, User user) throws IOException, ServletException {
//
//        String login = user.getLogin();
//        String password = user.getPassword();
//        String name = user.getName();
//        String email = user.getEmail();
//
//        Connection connection = null;
//        PreparedStatement statement = null;
//        try {
//
////            connection = DriverManager.getConnection("jdbc:sqlite:C:\\User\\i.vartanian\\Documents\\SQLite_db\\forum\\forum.db");
//
//            InitialContext context = new InitialContext();
//
//            connection = ((DataSource) context.lookup("jdbc/forum")).getConnection();
//
//            String sql = "INSERT INTO USERS (LOGIN, NAME, PASSWORD, EMAIL) VALUES (?, ?, ?, ?);";
//            statement = connection.prepareStatement(sql);
//            statement.setString(1, login);
//            statement.setString(2, name);
//            statement.setString(3, password);
//            statement.setString(4, email);
//
//            statement.executeUpdate();
//
//            setUserAttribute(request, user);
//
//            request.getRequestDispatcher("/auth").forward(request, response);
//            return;
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (NamingException e) {
//            e.printStackTrace();
//        } finally {
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }
//    }


//    public static User getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        String login = request.getParameter("login");
//        String password = request.getParameter("password");
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//
//        if (login == null | password == null){
//            return null;
//        }
//
//        String mdPassword = null;
//        try {
//            mdPassword = Utilities.mdGenerate(password);
//        } catch (NoSuchAlgorithmException e) {
//            return null;
//        }
//
//        return new User(login, mdPassword, name, email);
//
//    }


}
