import java.sql.*;
public class Balance {
    private int accno;
    private Connection con;
    private Account user;

    Balance(Connection con, Account user){
        this.accno = user.getAcc();
        this.con = con;
        this. user = user;
    }

    void getBalance() throws Exception{
        try{
            String query = "select balance from user_acc where acc = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, accno);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user.setBalance(rs.getInt("balance"));
            } else {
                System.out.println("Account not found");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }


}
