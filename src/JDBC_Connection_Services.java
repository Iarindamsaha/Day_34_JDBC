import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JDBC_Connection_Services {

    Connection connection;
    PreparedStatement preparedStatement;
    Scanner consoleInput = new Scanner(System.in);

    public void connection() {
        String useName = null;
        String passWord = null;
        
        try{
            System.out.print("Enter Username : ");
            useName = consoleInput.next();
            System.out.print("Enter Password : ");
            passWord = consoleInput.next();            
        }
        catch (InputMismatchException im){
            System.out.println("Enter Valid Input Only");
        }
       

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
        String name;
        int salary;

            System.out.print("Enter The Name : ");
            name = consoleInput.next();
            System.out.print("Enter Salary : ");
            salary = consoleInput.nextInt();

        try{

            preparedStatement = connection.prepareStatement("update employee_payroll set basic_pay = ? where Employee_name = ?");
            preparedStatement.setInt(1,salary);
            preparedStatement.setString(2,name);
            int update = preparedStatement.executeUpdate();


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

    public void getDataByName() {

        String name;

        System.out.print("Enter The Name : ");
        name = consoleInput.next();

        try {
            preparedStatement =connection.prepareStatement("Select * from employee_payroll where employee_name = ?");
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void getByDateRange() {

        String startingDate;
        String endingDate;

        System.out.print("Enter The StartingDate : ");
        startingDate = consoleInput.next();
        System.out.print("Enter The Ending Date : ");
        endingDate = consoleInput.next();

        try {
            preparedStatement =connection.prepareStatement("select * from employee_payroll where Starting_Date between cast(? as date) and date (?)");
            preparedStatement.setString(1, startingDate);
            preparedStatement.setString(2,endingDate);
            ResultSet resultSet = preparedStatement.executeQuery();
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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void getSalaryCalculationByGender() {

        String gender;
        ResultSet resultSet;

        System.out.print("Enter Gender : ");
        gender = consoleInput.next();

        System.out.println("Press 1 To Get Sum Of The Basic Pay" +
                "\nPress 2 To Get The AVG of Basic Pay" +
                "\nPress 3 To Get MIN Basic Pay" +
                "\nPress 4 To Get MAX Basic Pay" +
                "\nPress 5 To Get The Count" +
                "\nPress 6 To Return To The Main Menu\n");
        System.out.print("Your Choice :");
        int userInput = consoleInput.nextInt();

        switch (userInput){

            case 1: {
                try {
                    preparedStatement = connection.prepareStatement("select sum(basic_pay) from employee_payroll where gender = ? group by gender;");
                    preparedStatement.setString(1,gender);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        System.out.println("Value : " + resultSet.getInt(1));
                    }
                    getSalaryCalculationByGender();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

            case 2: {
                try {
                    preparedStatement = connection.prepareStatement("select avg(basic_pay) from employee_payroll where gender = ? group by gender;");
                    preparedStatement.setString(1,gender);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        System.out.println("Value : " + resultSet.getInt(1));
                    }
                    getSalaryCalculationByGender();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;

            }

            case 3: {
                try {
                    preparedStatement = connection.prepareStatement("select min(basic_pay) from employee_payroll where gender = ? group by gender;");
                    preparedStatement.setString(1,gender);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        System.out.println("Value : " + resultSet.getInt(1));
                    }
                    getSalaryCalculationByGender();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

            case 4 : {
                try {
                    preparedStatement = connection.prepareStatement("select max(basic_pay) from employee_payroll where gender = ? group by gender;");
                    preparedStatement.setString(1,gender);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        System.out.println("Value : " + resultSet.getInt(1));
                    }
                    getSalaryCalculationByGender();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }

            case 5: {
                try {
                    preparedStatement = connection.prepareStatement("select count(basic_pay) from employee_payroll where gender = ? group by gender;");
                    preparedStatement.setString(1,gender);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        System.out.println("Value : " + resultSet.getInt(1));
                    }
                    getSalaryCalculationByGender();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case 6 : {
                break;
            }
        }


    }

    public void addNewEmployeeData(){

                

    }

}
