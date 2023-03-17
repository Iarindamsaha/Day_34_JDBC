import java.util.Scanner;

public class JDBC_Main_Works {

    static Scanner consoleInput = new Scanner(System.in);


    public static void main(String[] args) {

        JDBC_Connection_Services services = new JDBC_Connection_Services();
        int userInput;

        do{
            System.out.println();
            System.out.println("--->Press 1 to Connect With The Database<---" +
                    "\n--->Press 2 To Get Data From Database<---" +
                    "\n--->Press 3 To Update Salary<---" +
                    "\n--->Press 10 to Exit The Program<---" +
                    "\n--->Press 11 To Close The Database Connection<---");

            System.out.println();
            System.out.print("Your Input : ");

            userInput = consoleInput.nextInt();

            switch (userInput){
                case 1: {

                    services.connection();
                    break;

                }
                case 2: {
                    services.getDataFromDatabase();
                    break;
                }

                case 3:{
                    services.updateData();
                    break;
                }

                case 10: {

                    System.out.println("---Application Closing---");
                    break;

                }

                case 11: {

                    services.closeConnection();
                    break;

                }
                default: {
                    System.out.println("Choose Correct Input");
                    break;
                }

            }

        }while (userInput!=10);




    }
}
