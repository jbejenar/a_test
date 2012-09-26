/**
 * Created with IntelliJ IDEA.
 * User: dmitriev
 * Date: 26/09/12
 * Time: 2:57 PM
 * To change this template use File | Settings | File Templates.
 */
import java.sql.*;

public class getMySqlConnection {
    public static void main(String[] args)
    {
        MySqlDb db = new MySqlDb();
        Connection conn=db.dbConnect(
                "jdbc:mysql://localhost:3306/testDb", "root", "");
    }

}

class MySqlDb
{
    public MySqlDb() {}

    public Connection dbConnect(String db_connect_string,
                                String db_userid, String db_password)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(
                    db_connect_string, db_userid, db_password);

            System.out.println("connected");
            return conn;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
