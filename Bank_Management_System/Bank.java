import java.util.*;
import java.security.SecureRandom;
import java.sql.*;
import java.time.LocalDateTime;

public class Bank {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Connection con = Database.getconnection();
        SecureRandom secure = new SecureRandom();
        System.out.println("----------------------");
        System.out.println("Bank Management System");
        System.out.println("----------------------");
        System.out.println("""
                Enter Your Choise...
                1.Create Bank Account
                2.Check Balance
                3.Credit
                4.Withdraw
                5.Transfer amount
                6.Transaction History
                7.Exit
                """);
        System.out.println("----------------------");
        System.out.print("Your Choise: ");
        int o = sc.nextInt();
        sc.nextLine();
        boolean t = true;
        while (t) {
            switch (o) {
                case 1 -> {
                    LocalDateTime dt = LocalDateTime.now();
                    Timestamp ts = Timestamp.valueOf(dt);
                    System.out.print("Enter Your Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter The Amount to Deposit in Your Account: ");
                    int balance = sc.nextInt();
                    Account acc = new Account(1000 + secure.nextInt(9000), name, balance, ts, con);
                }
                case 2 -> {
                    System.out.print("Enter Your acc no: ");
                    int acc = sc.nextInt();
                    Balance b = new Balance(acc, con);
                    System.out.println("Your Bank Balance: " + b.getBalance());
                }
                case 3 -> {
                    System.out.print("Enter Your acc no: ");
                    int acc = sc.nextInt();
                    System.out.print("Enter the Amount to credit: ");
                    int amt = sc.nextInt();
                    new Credit(acc, amt, con);
                }
                case 7 -> t = false;

                    
            }

        }

    }
}
