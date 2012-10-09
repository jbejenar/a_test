/**
 * Created with IntelliJ IDEA.
 * User: dmitriev
 * Date: 26/09/12
 * Time: 2:57 PM
 * To change this template use File | Settings | File Templates.
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class getMySqlConnection {

    protected Connection conn;
    protected String url;
    protected String user;
    protected String password;

    public Connection getMySqlConnection(String url, String user, String password) {
        //To change body of created methods use File | Settings | File Templates.
        getMySqlConnection.this.url = url;
        getMySqlConnection.this.user = user;
        getMySqlConnection.this.password = password;
        return  getMySqlConnection.this.dbConnect();

    }

        protected Connection dbConnect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                    url, user, password);

            System.out.println("connected to " + url );
            return conn;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
