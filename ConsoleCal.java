import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Console;

public class ConsoleCal{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        HashMap<String, String> Users = new HashMap<>();
        boolean loggedin = false;
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String loadedUser = parts[0];
                    String loadedPass = parts[1];
                    Users.put(loadedUser, loadedPass);
                }
            }

            reader.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        while (!loggedin) {
            System.out.println("\nChoice: \n" + "\n1. Register\n" + "2. Login\n" + "3. Exit\n");
            String option = sc.nextLine();
            
            if(option.equals("1")){
                System.out.println("Enter Username: ");
                String newuser = sc.nextLine();
                Console console = System.console();
                char[] passwordchar = console.readPassword("Password: ");
                String pass = new String(passwordchar);

                if (!IsValid(pass)) {
                    System.out.println(" Password must be at least 8 characters long and include:");
                    System.out.println("   - 1 uppercase letter");
                    System.out.println("   - 1 lowercase letter");
                    System.out.println("   - 1 digit");
                    System.out.println("   - 1 special character (!@#$%^&*()-+)");
                    System.out.println("   - No repeated adjacent characters");
                    continue;
                }

                if(Users.containsKey(newuser)){
                    System.out.println("Username already exists, Enter different username");
                }
                else{
                    Users.put(newuser, pass);
                    System.out.println("Registration Successful!");
                    try{
                        FileWriter fw = new FileWriter("users.txt", true);
                        String line = newuser + "," + pass + "\n";
                        fw.write(line);
                        fw.close();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }                
            }
            else if(option.equals("2")){
                System.out.print("Username: ");
                String inputuser = sc.nextLine();

                Console console = System.console();
                char[] passwordchar = console.readPassword("Password: ");
                String pass = new String(passwordchar);


                if(Users.containsKey(inputuser) && Users.get(inputuser).equals(pass)){
                    System.out.println("Login Successful!");
                    loggedin = true;
                }
                else{
                System.out.println("Invalid Credentials!");
                }
            }
            else if(option.equals("3")){
                System.out.println("Logging Off!");
                return;
            }

       
        }
        
        if(loggedin){
            while(true){
                System.out.print("\n\nMenu: \n" + "1. Addition of 2 Numbers.\n" + "2. Subtraction of 2 numbers.\n" + "3. Mulplication of 2 Numbers.\n" + "4. Fibonacci Series.\n" + "5. Factorial of a Number.\n" + "6. Exit.\n"+ "Enter your choice: ");
                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        Addition(sc);
                        break;
                    case "2":
                        Subtraction(sc);
                        break;
                    case "3":
                        Multiplication(sc);
                        break;
                    case "4":
                        Fibonacci(sc);
                        break;
                    case "5":
                        Factorial(sc);
                        break;
                    case "6":
                        Exit(sc);
                        return;
                    default:
                        System.out.println("Invalid choice! Try again.");
                        break;
                }
            
            }
        }
        else{
            System.out.println("Invalid Input! Account locked.");
        }
        sc.close();
    }
    public static boolean IsValid(String passwd){
        if(passwd.length() < 8){
            return false;
        }
        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        for(int i = 0; i < passwd.length(); i++){
            char c = passwd.charAt(i);
            if (i > 0 && passwd.charAt(i) == passwd.charAt(i - 1)) {
                return false;
            }
            if(Character.isLowerCase(c)){
                hasLowerCase = true;
            }
            if(Character.isUpperCase(c)){
                hasUpperCase = true;
            }
            if(Character.isDigit(c)){
                hasDigit = true;
            }
            if("!@#$%^&*()-+".indexOf(c) >= 0){
                hasSpecial = true;
            }
        }
        return hasDigit && hasLowerCase && hasSpecial && hasUpperCase;
    }

    public static void Addition(Scanner sc) {
        System.out.print("Enter First Number: ");
        int num1 = sc.nextInt();
        System.out.print("Enter Second Number: ");
        int num2 = sc.nextInt();
        sc.nextLine();
        System.out.println("Addition of 2 Numbers is: " + (num1+num2) );
    }

    public static void Subtraction(Scanner sc) {
        System.out.print("Enter First Number: ");
        int nums1 = sc.nextInt();
        System.out.print("Enter Second Number: ");
        int nums2 = sc.nextInt();
        sc.nextLine();
        System.out.println("Subtraction of 2 Numbers is: " + (nums1-nums2));
    }

    public static void Multiplication(Scanner sc){
        System.out.print("Enter First Number: ");
        int numbs1 = sc.nextInt();
        System.out.print("Enter Second Number: ");
        int numbs2 = sc.nextInt();
        sc.nextLine();
        System.out.println("Multiplication of 2 Numbers is: " + (numbs1*numbs2));
    }

    public static void Exit(Scanner sc) {
        System.out.println("Exiting the Menu...");
    }

    public static void Factorial(Scanner sc){
        int fact = 1;
        System.out.print("Enter the Number: ");
        int num = sc.nextInt();
        sc.nextLine();

        for(int j=1; j<=num; j++){
            fact = fact * j;
        }
        System.out.println("Factorial of " + num +" is" + fact);
    }

    public static void Fibonacci(Scanner sc) {
        int a = 0;
        int b = 1;
        System.out.print("Enter the Number of terms you want: ");
        int terms = sc.nextInt();
        sc.nextLine();
        System.out.println("Fibonacci Series: " );

        for(int i = 1; i <= terms; i++){
            System.out.print(a + " ");
            int next = a + b;
            a = b;
            b = next;
        } 
    }
}
    