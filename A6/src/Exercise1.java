/***
 * Program that validate a member’s login.
 * All users' login info in "Exercise1Input.txt".
 *
 * @author Jun Gao - B00899189
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
//StringTokenizer to handle input file
import java.util.StringTokenizer;

public class Exercise1 {
    public static void main(String[] args) throws FileNotFoundException {

        //Create two HashMaps
        HashMap<String,String> usernamePassword = new HashMap<String,String>();
        HashMap<String,String> usernameFullName = new HashMap<String,String>();

        File file = new File("Exercise1Input.txt");

        Scanner inputFile = new Scanner(file);

        //Read file and set HashMaps
        while (inputFile.hasNextLine()) {
            StringTokenizer token = new StringTokenizer(inputFile.nextLine(), "\t");
            String fullName = token.nextToken();
            String username = token.nextToken();
            String password = token.nextToken();
            usernamePassword.put(username,password);
            usernameFullName.put(username,fullName);
        }

        inputFile.close();

        Scanner in = new Scanner(System.in);

        //Let user login in
        for (int i=1; i<=3; i++) {
            System.out.print("Login: ");
            String username = in.nextLine();
            System.out.print("Password: ");
            String password = in.nextLine();

            //Test if username and password match
            if (password.equals(usernamePassword.get(username))) {
                System.out.println("\nLogin successful");
                System.out.println("Welcome " + usernameFullName.get(username));
                break;
            }

            //Error messages
            if (i<3) {
                System.out.println("\nEither the username or password is incorrect. You have " + (3-i) + " more attempts.\n");
            } else {
                System.out.println("\nSorry. Incorrect login. Please contact the system administrator.");
            }
        }
    }
}