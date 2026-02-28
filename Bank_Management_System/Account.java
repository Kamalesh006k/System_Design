import java.util.*;
import java.math.BigDecimal;
import java.sql.*;

class Account {
    private int id;
    private int acc;
    private String name;
    private int pin;
    private BigDecimal balance;
    private Timestamp doj;

    public int getAcc() {
        return acc;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPin() {
        return pin;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Timestamp getDoj() {
        return doj;
    }

    public void setAcc(int acc) {
        this.acc = acc;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setDoj(Timestamp doj) {
        this.doj = doj;
    }

    public void setNull(){
        this.acc = 0;
        this.name = null;
        this.pin = 0;
        this.balance = BigDecimal.ZERO;
        this.doj = null;
    }
    Account(){
        setNull();
    }

    Account(int acc, String name, int pin, Timestamp doj,Connection con) throws Exception{
        this.acc = acc;
        this.name = name;
        this.pin = pin;
        this.doj = doj;
        
        // String query = "insert into user_acc(acc,name,pin,balance,join_date) values(?,?,?,?,?)";
        // PreparedStatement st = con.prepareStatement(query);
        // st.setInt(1,acc);
        // st.setString(2, name);
        // st.setInt(3, pin);
        // st.setInt(4, 0);
        // st.setTimestamp(5, doj);

        // int result = st.executeUpdate();
        // if(result == 1){
        //     System.out.println("Bank account created");
        // }else{
        //     System.out.println("Bank account not created");
        // }

        // String query1 = "select * from user_acc where acc = ? && pin = ?";
        // PreparedStatement st1 = con.prepareStatement(query1);
        // st1.setInt(1, acc);
        // st1.setInt(2, pin);

        // ResultSet rs = st1.executeQuery();
        // if(rs.next()){
        //     id = rs.getInt(1);
        // }
    }
}
