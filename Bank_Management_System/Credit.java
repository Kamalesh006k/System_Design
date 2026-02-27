import java.sql.*;
public class Credit {
    private int acc;
    private int Amount;
    private Connection con;
    private Account user;
    private Balance b;

    // private void getBalance() throws Exception {
    //     try {
    //         String query = "select balance from user_acc where acc = ?";
    //         PreparedStatement st = con.prepareStatement(query);
    //         st.setInt(1, acc);
    //         ResultSet rs = st.executeQuery();
    //         rs.next();
    //         if (rs.next()) {
    //             balance = rs.getInt("balance");
    //         } else {
    //             throw new Exception("Account not found");
    //         }
    //     }catch(Exception e){
    //         System.out.println(e);
    //     }
        
    // }

    Credit(int Amount, Connection con, Account user, Balance b, Timestamp ts) throws Exception{
        this.acc = user.getAcc();
        this.Amount = Amount;
        this.con = con;
        this.user = user;
        this.b = b;

        con.setAutoCommit(false);

        try{
            String query = "update user_acc set balance = balance + ? where acc = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, Amount);
            st.setInt(2,user.getAcc());
            int row = st.executeUpdate();
            if(row == 1) System.out.println(Amount+" has been credited to your bank account, Account No: "+user.getAcc());
            b.getBalance();
            System.out.println("Balance: "+ user.getBalance());
    
            String query1 = "Insert into transaction(type,amt,acc_holder,timestamp) values('credit',?,?,?)";
            PreparedStatement st1 = con.prepareStatement(query1);
            st1.setInt(1,Amount);
            st1.setInt(2, user.getAcc());
            st1.setTimestamp(3,ts);
            st1.executeUpdate();
        }catch(Exception e){
            con.rollback();
            throw new Exception("Operation failed");
        }

        con.commit();
        con.setAutoCommit(true);
    }
}
