import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static void main(String[] args){
        String jdbcUrl = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String username = "root";
        String password = "793789msw";
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("connection Established");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Connection to database : "+jdbcUrl);
            connection= DriverManager.getConnection(jdbcUrl,username,password);
            System.out.println("connected to DB : "+connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
