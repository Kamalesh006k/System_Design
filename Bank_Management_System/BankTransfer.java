import java.math.BigDecimal;
import java.sql.*;

public class BankTransfer extends Transaction {

    private TransactionUpdate td;

    BankTransfer(Account user, int rec, BigDecimal amt, Connection con, Balance b, Timestamp ts, TransactionUpdate tc,
            TransactionUpdate td) {
        this.user = user;
        this.Amount = amt;
        this.acc = rec;
        this.b = b;
        this.con = con;
        this.ts = ts;
        this.tu = tc;
        this.td = td;
    }

    @Override
    void process() throws Exception {

        con.setAutoCommit(false);

        try {

            if (user.getBalance().compareTo(Amount) < 0) {
                throw new Exception("Insufficient Balance");
            }

            String debitQuery = "update user_acc set balance = balance - ? where acc = ?";
            PreparedStatement st1 = con.prepareStatement(debitQuery);
            st1.setBigDecimal(1, Amount);
            st1.setInt(2, user.getAcc());

            if (st1.executeUpdate() != 1)
                throw new Exception("Debit failed");

            String creditQuery = "update user_acc set balance = balance + ? where acc = ?";
            PreparedStatement st2 = con.prepareStatement(creditQuery);
            st2.setBigDecimal(1, Amount);
            st2.setInt(2, acc);

            if (st2.executeUpdate() != 1)
                throw new Exception("Credit failed");
            td.update();
            tu.update();
            con.commit();
            System.out.println("Transfer Successful!");

        } catch (Exception e) {
            con.rollback();
            throw new Exception("Transfer Failed. Rolled back.");
        }

        con.setAutoCommit(true);
    }
}
