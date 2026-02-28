import java.sql.*;
public class TransactionHistory {
    private Account user;

    TransactionHistory(Account acc) throws Exception{
        this.user = acc;

        String query = "select * from transaction where acc_holder = ?";
        PreparedStatement st = user.getCon().prepareStatement(query);
        st.setInt(1, user.getAcc());

        ResultSet rs = st.executeQuery();

        while(rs.next()){
            System.out.printf("%-8s %12s %5d \n",rs.getString(2),rs.getBigDecimal(3).toPlainString(),rs.getInt(4));
        }
    }
}
