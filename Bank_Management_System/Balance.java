import java.sql.*;
public class Balance {
    private int accno;
    private Connection con;
    private Account user;

    Balance(Account user){
        this.accno = user.getAcc();
        this.con = user.getCon();
        this.user = user;
    }

    void getBalance() throws Exception{
        try{
            String query = "select balance from user_acc where acc = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, accno);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user.setBalance(rs.getBigDecimal("balance"));
            } else {
                System.out.println("Account not found");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }


}
