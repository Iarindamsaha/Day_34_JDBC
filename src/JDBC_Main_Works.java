import java.util.Scanner;

public class JDBC_Main_Works {

    static Scanner consoleInput = new Scanner(System.in);


    public static void main(String[] args) {

        JDBC_Connection_Services services = new JDBC_Connection_Services();
        int userInput;

        do{
            System.out.println();
            System.out.println("--->Press 1 to Connect With The Database<---" +
                    "\n--->Press 10 to Exit The Program<---");

            System.out.println();
            System.out.print("Your Input : ");

            userInput = consoleInput.nextInt();

            switch (userInput){
                case 1: {

                    services.connection();
                    break;

                }

                case 10: {

                    System.out.println("---Application Closing---");
                    break;

                }

            }

        }while (userInput!=10);




    }
}
