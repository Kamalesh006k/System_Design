import java.sql.Timestamp;
import java.util.*;
import java.sql.*;

class Account {
    private int acc;
    private String name;
    private int balance;
    private Timestamp doj;
    private Connection Con;

    Account(int acc, String name, int balance, Timestamp doj,Connection con) throws Exception{
        this.acc = acc;
        this.name = name;
        this.balance = balance;
        this.doj = doj;
        this.Con = con;
        
        String query = "insert into user_acc(acc,name,balance,join_date) values(?,?,?,?)";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1,acc);
        st.setString(2, name);
        st.setInt(3, balance);
        st.setTimestamp(4, doj);

        int result = st.executeUpdate();
        if(result == 1){
            System.out.println("Bank account created");
        }else{
            System.out.println("Bank account not created");
        }
    }
}
