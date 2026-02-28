import java.sql.*;
public class TransactionHistory extends Transaction{
    private Account user;

    TransactionHistory(Account acc){
        this.user = acc;
    }

    @Override
    void process() throws Exception{
        String query = "select * from transaction where acc_holder = ?";
        PreparedStatement st = user.getCon().prepareStatement(query);
        st.setInt(1, user.getAcc());

        ResultSet rs = st.executeQuery();
        System.out.println("    Transaction History   ");
        System.out.println("--------------------------");
        while(rs.next()){
            System.out.printf("%-8s|%8s|%7d\n",rs.getString(2),rs.getBigDecimal(3).toPlainString(),rs.getInt(4));
            System.out.println("--------------------------");
        }
    }
}
