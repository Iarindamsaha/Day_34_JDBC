import java.sql.*;
import java.util.Scanner;

public class JDBC_Connection_Services {

    Connection connection;
    Scanner consoleInput = new Scanner(System.in);

    public void connection() {

        System.out.print("Enter Username : ");
        String useName = consoleInput.next();
        System.out.print("Enter Password : ");
        String passWord = consoleInput.next();

        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service";



        try {

            System.out.println("Connecting To Database.................");
            connection = DriverManager.getConnection(jdbcURL, useName, passWord);
            System.out.println("<<<<<---Connection Successful--->>>>");

        } catch (Exception e) {
            System.out.println("+++++++++++++Connection Failed+++++++++++++++");
            System.out.println("---------Check UserName and Password--------");
        }

    }

    public void closeConnection(){

        try {
            connection.close();
            System.out.println("---Database Connection Closed---");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (NullPointerException np){
            System.out.println("----Database Connection Never Started----");
        }

    }

    public void getDataFromDatabase() {

        try{

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select *  from employee_payroll");

            while(resultSet.next()){

                System.out.println("-----------------------------------------------------------");
                System.out.println("Emp Id: " + resultSet.getInt(1));
                System.out.println("Employee Name : " + resultSet.getString(2));
                System.out.println("Employee PhoneNumber : " + resultSet.getLong(3));
                System.out.println("Department : " + resultSet.getString(4));
                System.out.println("Gender : " + resultSet.getString(5));
                System.out.println("Basic_Pay : " + resultSet.getDouble(6));
                System.out.println("Deduction : " + resultSet.getDouble(7));
                System.out.println("Taxable_Pay : " + resultSet.getDouble(8));
                System.out.println("Tax : " + resultSet.getDouble(9));
                System.out.println("Net Pay : " + resultSet.getDouble(10));
                System.out.println("Job Starting Date : " + resultSet.getDate(11));
                System.out.println("-----------------------------------------------------------");

            }

        }
        catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }

    public void updateData(){


        try{

            String updateSQL = "update employee_payroll set basic_pay = 30000 where employee_name = \"Terrisa\"";
            Statement statement = connection.createStatement();
            int update = statement.executeUpdate(updateSQL);


            if (update > 0){
                System.out.println("Data Successfully Updated : " + update + " Rows Updated");
            }
            else{
                System.out.println("Data Not Added");
            }
        }
        catch (Exception e){

            System.out.println("Field Not Available");
        }

    }

}
