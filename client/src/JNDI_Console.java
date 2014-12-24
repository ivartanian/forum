import com.core.model.beans.ForumWorkRemote;
import weblogic.jndi.WLInitialContextFactory;

import javax.naming.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

/**
 * Created by i.vartanian on 13.12.2014.
 */
public class JNDI_Console {

    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> aClass = Class.forName("weblogic.jndi.WLInitialContextFactory");

        Context context = null;
        Hashtable env = new Hashtable<>();
        env.put(Context.PROVIDER_URL, "t3://62.80.177.154:80");
        env.put(Context.INITIAL_CONTEXT_FACTORY, WLInitialContextFactory.class.getName());
        Connection connection = null;
        try {

            context = new InitialContext(env);

//            NamingEnumeration<NameClassPair> list = context.list("jdbc");
//            while (list.hasMoreElements()){
//                System.out.println(list.next().toString());
//            }
//
//            context.rebind("count", ((Long)context.lookup("count") + 100));
//            System.out.println(context.lookup("count"));

            Object forumJDBC = context.lookup("jdbc/forum");
            connection = ((DataSource)forumJDBC).getConnection();
            System.out.println(connection.getMetaData().getDriverVersion());

//            ForumWorkRemote forumWorkRemote = (ForumWorkRemote) context.lookup("ForumFacadeBean#com.core.model.beans.ForumWorkRemote");
//
//            System.out.println(forumWorkRemote);

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

}
