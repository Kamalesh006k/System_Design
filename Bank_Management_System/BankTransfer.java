import java.math.BigDecimal;

public class BankTransfer {
    private Account user;
    int rec;
    BigDecimal amt;
    Transaction b;
    Transaction c;

    BankTransfer(Account user, int rec, BigDecimal amt, Transaction b, Transaction c){
        this.user = user;
        this.rec = rec;
        this.amt = amt;
        this.b = b;
        this.c = c;

        
    }




}
