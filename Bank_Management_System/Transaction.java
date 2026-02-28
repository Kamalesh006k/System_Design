import java.math.BigDecimal;
import java.sql.Connection;

abstract class Transaction {
    private int acc;
    private BigDecimal Amount;
    private Connection con;
    private Account user;
    private Balance b;
}
