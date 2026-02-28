import java.math.BigDecimal;
import java.sql.*;
public class Credit extends Transaction{
    Credit(BigDecimal Amount, Connection con, Account user, Balance b, Timestamp ts, TransactionUpdate tu){
        this.Amount = Amount;
        this.con = con;
        this.user = user;
        this.b = b;
        this.ts = ts;
        this.tu = tu;
    }

    @Override
    void process() throws Exception {
        if (Amount == null || Amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("Amount must be greater than 0");
        }
        con.setAutoCommit(false);

        try{
            String query = "update user_acc set balance = balance + ? where acc = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setBigDecimal(1, Amount);
            st.setInt(2,user.getAcc());
            int row = st.executeUpdate();
            if(row == 1) System.out.println(Amount+" has been credited to your bank account, Account No: "+user.getAcc());
            b.getBalance();
            System.out.println("Balance: "+ user.getBalance());
            tu.update();
        }catch(Exception e){
            con.rollback();
            throw new Exception("Operation failed");
        }

        con.commit();
        con.setAutoCommit(true);
    }
}
