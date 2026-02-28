import java.math.BigDecimal;
import java.sql.*;
public class BankTransfer extends Transaction{
    
    BankTransfer(Account user, int rec, BigDecimal amt, Connection con, Balance b, Timestamp ts){
        this.user = user;
        this.Amount = amt;
        this.acc = rec;
        this.b = b;
        this.con = con;
        this.ts = ts;
    }

    @Override
    void process() throws Exception{
        String query1 = "update user_acc set balance = balance - ? where acc = ?";
        String query2 = "update user_acc set balance = balance + ? where acc = ?";
        if(user.getBalance().compareTo(Amount)>=0){
            PreparedStatement st1 = con.prepareStatement(query1);
            st1.setBigDecimal(1, Amount);
            st1.setInt(2, user.getAcc());
            int rs = st1.executeUpdate();
            if(rs == 1){
                System.out.println(Amount+" has been debited from your bank account, Account No: "+user.getAcc());
                b.getBalance();
                System.out.println("Current Balance: "+user.getBalance());

                PreparedStatement st2 = con.prepareStatement(query2);
                st2.setBigDecimal(1, Amount);
                st2.setInt(2, acc);
                st2.executeUpdate();
            }else{
                System.out.println("An Error Occured!");
            }
        }else{
            System.out.println("Insufficient Balance! Enter Valid Amount");
        }
    }
}
