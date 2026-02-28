import java.math.BigDecimal;
import java.sql.*;
public class Withdraw extends Transaction {
    Withdraw(BigDecimal Amount, Connection con, Account user, Balance b, Timestamp ts) throws Exception{
        this.Amount = Amount;
        this.con = con;
        this.user = user;
        this.b = b;
        this.ts = ts;
    }

    @Override
    void process() throws Exception {
        con.setAutoCommit(false);

        try{
            if (user.getBalance().compareTo(Amount) < 0) {
                System.out.println("Insufficient Balance");
            } else {
                String query = "update user_acc set balance = balance - ? where acc = ?";
                PreparedStatement st = con.prepareStatement(query);
                st.setBigDecimal(1, Amount);
                st.setInt(2, user.getAcc());
                int row = st.executeUpdate();
                if (row == 1)
                    System.out
                            .println(Amount + " has been debited from your bank account, Account No: " + user.getAcc());
                b.getBalance();
                System.out.println("Balance: " + user.getBalance());

                String query1 = "Insert into transaction(type,amt,acc_holder,timestamp) values('debit',?,?,?)";
                PreparedStatement st1 = con.prepareStatement(query1);
                st1.setBigDecimal(1, Amount);
                st1.setInt(2, user.getAcc());
                st1.setTimestamp(3, ts);
                st1.executeUpdate();
            }
        } catch (Exception e) {
            con.rollback();
            throw new Exception("Operation Failed");
        }
        con.commit();
        con.setAutoCommit(true);

    }
}
