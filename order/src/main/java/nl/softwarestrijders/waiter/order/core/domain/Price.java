package nl.softwarestrijders.waiter.order.core.domain;

import nl.softwarestrijders.waiter.order.common.annotation.TestExcludeGenerated;

// This class doesn't do anything atm and is useless to test.
@TestExcludeGenerated
public class Price {

    private double subTotal;
    private double total;
    private double vat = 0.21f;

    public Price(double subTotal, double total, double vat) {
        this.subTotal = subTotal;
        this.total = total;
        this.vat = vat;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    //TODO: Once RabbitMQ works we need to get the product prices and calculate the total here
    public static Price calculatePrice(Receipt receipt) {
        return new Price(5.00, 6.00, 0.21);
    }
}
