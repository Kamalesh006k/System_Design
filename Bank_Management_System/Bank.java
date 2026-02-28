import java.util.*;
import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.*;
import java.time.LocalDateTime;

public class Bank {
    static private Account user;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Connection con = Database.getconnection();
        SecureRandom secure = new SecureRandom();
        System.out.println("----------------------");
        System.out.println("Bank Management System");
        System.out.println("----------------------");
        while (user == null) {
            System.out.println("""
                    Enter Your Choise...
                    1.Create Bank Account
                    2.Login
                    3.Exit""");
            System.out.println("----------------------");
            System.out.print("Your Choise: ");
            int o = sc.nextInt();
            System.out.println("----------------------");
            sc.nextLine();
            switch (o) {
                case 1 -> {
                    LocalDateTime dt = LocalDateTime.now();
                    Timestamp ts = Timestamp.valueOf(dt);
                    System.out.print("Enter Your Name: ");
                    String name = sc.nextLine();
                    System.out.print("Create a 4-Digit Pin: ");
                    int pin = sc.nextInt();
                    user = new Account(1000 + secure.nextInt(9000), name, pin, ts, con);
                    new AccountDAO(user,con).createUser();
                }
                case 2 -> {
                    System.out.print("Enter Your 4-Digit acc no: ");
                    int acc = sc.nextInt();
                    System.out.print("Enter your 4-Digit pin: ");
                    int pin = sc.nextInt();
                    user = new Login(acc, pin, con).login();
                }
                case 3 -> {
                    System.out.println("Thank you for using Bank System.");
                    System.exit(0);
                }
            }
            boolean t = true;
            if(user != null){

                while(t){
                    System.out.println("----------------------");
                    System.out.println("""
                        1.Check Balance
                        2.Credit
                        3.Withdraw
                        4.Transfer amount
                        5.Transaction History
                        6.Logout""");
                    System.out.println("----------------------");
                    System.out.print("Your Choise: ");
                    int option = sc.nextInt();
                    System.out.println("----------------------");
                    sc.nextLine();
                    switch (option) {
                        case 1 -> {
                            Balance b = new Balance(user,con);
                            b.getBalance();
                            System.out.println("Your Bank Balance: " + user.getBalance());
                        }
                        case 2 -> {
                            LocalDateTime lt = LocalDateTime.now();
                            Timestamp ts = Timestamp.valueOf(lt);
                            System.out.print("Enter the Amount to credit: ");
                            BigDecimal amt = sc.nextBigDecimal();
                            Transaction cd = new Credit(amt, con, user, new Balance(user,con),ts);
                            cd.process();
                        }
                        case 3 -> {
                            LocalDateTime lt = LocalDateTime.now();
                            Timestamp ts = Timestamp.valueOf(lt);
                            System.out.print("Enter the Amount to withdraw: ");
                            BigDecimal amt = sc.nextBigDecimal();
                            Transaction wd = new Withdraw(amt, con, user, new Balance(user,con),ts);
                            wd.process();
                        }
                        case 4 ->{
                            LocalDateTime lt = LocalDateTime.now();
                            Timestamp ts = Timestamp.valueOf(lt);
                            Balance b = new Balance(user, con);
                            System.out.print("Enter the Receiver's Account No: ");
                            int acc = sc.nextInt();
                            System.out.print("Enter the Amount to Transfer: ");
                            BigDecimal amt = sc.nextBigDecimal();
                            BankTransfer bt = new BankTransfer(user,acc,amt, con, b, ts);
                            bt.process();

                        }
                        case 5 ->{
                            Transaction th = new TransactionHistory(user,con);
                            th.process();
                        }
                        case 6 ->{
                            user = null;
                            t = false;
                        }
                    }
                }
            }
    
        }
        }
}
