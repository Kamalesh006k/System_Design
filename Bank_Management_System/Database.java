import java.sql.*;
public class Database {

    static Connection getconnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/bank_management_system";
        String user = "root";
        String password = "admin@123";
        return DriverManager.getConnection(url, user, password);
    }
}
