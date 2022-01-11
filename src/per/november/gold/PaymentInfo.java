package per.november.gold;

import java.io.Serializable;

public class PaymentInfo implements Serializable {
    static private String cardNumber;
    transient private int price;
    private String payDay;
    private String cardIssuer;
}
