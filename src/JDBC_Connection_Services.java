import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC_Connection_Services {

    public void connection() {

        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";
        String useName = "root";
        String passWord = "Killjoy@15071998";
        Connection connection;

        try {

            System.out.println("Connecting To Database : " + jdbcURL);
            connection = DriverManager.getConnection(jdbcURL, useName, passWord);
            System.out.println("<<<<<---Connection Successful--->>>>");

        } catch (Exception e) {
            System.out.println("+++++++++Connection Failed+++++++++");
        }

    }

}
