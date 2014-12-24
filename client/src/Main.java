import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * Created by i.vartanian on 13.12.2014.
 */

public class Main extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try(ServletOutputStream outputStream = resp.getOutputStream();) {

            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()){
                outputStream.print("<br>");
                outputStream.print(drivers.nextElement().toString());
            }

            outputStream.print("<hr>");

            InitialContext initialContext = new InitialContext();
            NamingEnumeration<NameClassPair> list = initialContext.list("jdbc");
            while (list.hasMoreElements()){
                outputStream.print("<br>");
                outputStream.print(list.nextElement().toString());
            }

            try {

                initialContext.bind("count", ((Long)initialContext.lookup("count") + 1));

            }catch (Exception e){
                initialContext.rebind("count", ((Long)initialContext.lookup("count") + 1));
            }

            outputStream.print("<hr>");
            outputStream.print((Long)initialContext.lookup("count"));

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }



}
