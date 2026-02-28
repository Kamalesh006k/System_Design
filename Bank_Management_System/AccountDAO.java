import java.sql.PreparedStatement;
import java.sql.*;

public class AccountDAO {
    Account user;
    Connection con;

    AccountDAO(Account user, Connection con) throws Exception{
        this.user = user;
        this.con = con;
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
