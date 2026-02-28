import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class AccountDAO {
    Account user;
    Connection con;

    AccountDAO(Account user, Connection con) throws Exception{
        this.user = user;
        this.con = con;
        // String query1 = "select * from user_acc where acc = ? && pin = ?";
        // PreparedStatement st1 = user.getCon().prepareStatement(query1);
        // st1.setInt(1, user.getAcc());
        // st1.setInt(2, user.getPin());

        // ResultSet rs = st1.executeQuery();
        // if(rs.next()){
        //     user.setId(rs.getInt(1));
        // }
    }

    void createUser() throws Exception{
        String query = "insert into user_acc(acc,name,pin,balance,join_date) values(?,?,?,?,?)";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1,user.getAcc());
        st.setString(2, user.getName());
        st.setInt(3, user.getPin());
        st.setInt(4, 0);
        st.setTimestamp(5, user.getDoj());

        int result = st.executeUpdate();
        if(result == 1){
            System.out.println("Bank account created");
        }else{
            System.out.println("Bank account not created");
        }
    }
}
