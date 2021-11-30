package nl.softwarestrijders.waiter.order.domain;

public class Price {

    private double subTotal;
    private double total;
    private double vat = 0.21f;

    public Price() {}
    public Price(double subTotal, double total, double vat) {
        this.subTotal = subTotal;
        this.total = total;
        this.vat = vat;
    }

    public static Price calculatePrice(Receipt reciept) {
        return new Price();
    }
}
