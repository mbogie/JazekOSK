package jazekOSK.type;

public enum PaymentType {
    CASH("CASH"),
    CREDIT_CARD("CREDIT_CARD"),
    DEBIT_CARD("DEBIT_CARD"),
    MONEY_TRANSFER("MONEY_TRANSFER");

    private String type;

    PaymentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
