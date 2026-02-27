import java.sql.*;
public class Credit {
    private int acc;
    private int Amount;
    private Connection con;
    private int balance;

    private void getBalance() throws Exception {
        try {
            String query = "select balance from user_acc where acc = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, acc);
            ResultSet rs = st.executeQuery();
            rs.next();
            if (rs.next()) {
                balance = rs.getInt("balance");
            } else {
                throw new Exception("Account not found");
            }
        }catch(Exception e){
            System.out.println(e);
        }
        
    }

    Credit(int acc, int Amount, Connection con) throws Exception{
        this.acc = acc;
        this.Amount = Amount;
        this.con = con;

        String query = "update user_acc set balance = balance + ? where acc = ?";
        PreparedStatement st = con.prepareStatement(query);
        getBalance();
        st.setInt(1, Amount);
        st.setInt(2,acc);
        int row = st.executeUpdate();
        if(row == 1) System.out.println(Amount+" has been credited to your bank account, Account No: "+acc);
        getBalance();
        System.out.println("Balance: "+balance);
    }
}
