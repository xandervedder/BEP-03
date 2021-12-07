package nl.softwarestrijders.waiter.order.domain;

public class Price {

    private double subTotal;
    private double total;
    private double vat = 0.21f;

    public Price(double subTotal, double total, double vat) {
        this.subTotal = subTotal;
        this.total = total;
        this.vat = vat;
    }

    //TODO: Remove test values
    public static Price calculatePrice(Receipt receipt) {
        return new Price(5.00, 6.00, 0.21);
    }
}
