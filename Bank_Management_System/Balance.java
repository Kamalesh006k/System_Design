import java.sql.*;
public class Balance {
    private int accno;
    Connection con;

    Balance(int acc, Connection con){
        this.accno = acc;
        this.con = con;
    }

    int getBalance() throws Exception{
        try{
            String query = "select balance from user_acc where acc = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, accno);
            ResultSet rs = st.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
        
    }


}
