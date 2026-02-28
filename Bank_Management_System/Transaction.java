import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;

abstract class Transaction {
    int acc;
    BigDecimal Amount;
    Connection con;
    Account user;
    Balance b;
    Timestamp ts;
    TransactionUpdate tu;

    abstract void process() throws Exception;
}
