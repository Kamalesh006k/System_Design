import java.math.BigDecimal;
import java.sql.*;

public class TransactionUpdate {
    Connection con;
    BigDecimal Amount;
    Timestamp ts;
    String type;
    int acc;


    TransactionUpdate(Connection con, String type, BigDecimal Amount, Timestamp ts,int acc){
        this.Amount = Amount;
        this.con = con;
        this.ts = ts;
        this.type = type;
        this.acc = acc;
    }

    void update() throws Exception{
        try{
            String query1 = "Insert into transaction(type,amt,acc_holder,timestamp) values(?,?,?,?)";
            PreparedStatement st1 = con.prepareStatement(query1);
            st1.setString(1, type);
            st1.setBigDecimal(2,Amount);
            st1.setInt(3, acc);
            st1.setTimestamp(4,ts);
            st1.executeUpdate();
        }catch(Exception e){
            throw new Exception("Operation Failed");
        }
    }
}
