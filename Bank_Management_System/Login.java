import java.sql.*;
public class Login {
    Account user;
    int acc;
    int pin;
    Connection con;

    Login(int acc, int pin, Connection con) throws Exception{
        this.user = new Account();
        this.acc = acc;
        this.pin = pin;
        this.con = con;

        String query = "select * from user_acc where acc = ? && pin = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1, acc);
        st.setInt(2, pin);

        ResultSet rs = st.executeQuery();
        if(rs.next()){
            user.setId(rs.getInt(1));
            user.setAcc(rs.getInt(2));
            user.setName(rs.getString(3));
            user.setBalance(rs.getInt(5));
            user.setDoj(rs.getTimestamp(6));
            user.setPin(rs.getInt(4));
        }
    }

    Account login(){
        return user;
    }
}
